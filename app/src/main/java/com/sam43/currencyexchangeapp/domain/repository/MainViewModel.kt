package com.sam43.currencyexchangeapp.domain.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.domain.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: ConversionUseCases,
    private val dispatchers: DispatcherProvider,
    private val workManager: WorkManager
): ViewModel() {

    sealed class CurrencyEvent {
        class SuccessResponse(val response: CurrencyResponse?): CurrencyEvent()
        class SuccessListResponse<T>(val list: MutableList<T>?): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        class ConnectionFailure(val errorText: String): CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _savedWorkInfo = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val savedWorkInfo: StateFlow<CurrencyEvent> = _savedWorkInfo
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion
    private val _conversionRates = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversionRates: StateFlow<CurrencyEvent> = _conversionRates

    fun consumeAllRatesByBase(
        base: String? = "USD"
    ) {
        /*option to set custom 'base' currency param (need subscription for this api)*/
        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading
            base?.let {
                useCases.getRates(it)
                    .onEach { response ->
                        when (response) {
                            is Resource.Loading -> _conversion.value =
                                CurrencyEvent.Loading
                            is Resource.NoInternet -> _conversion.value =
                                CurrencyEvent.ConnectionFailure(response.message!!)
                            is Resource.Error -> _conversion.value =
                                CurrencyEvent.Failure(response.message!!)
                            is Resource.Success -> _conversion.value =
                                CurrencyEvent.SuccessResponse(response.data)
                        }
                    }.launchIn(this)
            }
        }
    }

    fun convert(
        amountStr: String,
        from: String? = "USD",
        to: String?
    ) {
        val fromAmount = amountStr.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(dispatchers.io) {
            from?.let {
                useCases.getConvertedRates(amount = amountStr, from = it, to = to.toString())
                    .onEach { response ->
                        when (response) {
                            is Resource.Loading -> _conversionRates.value =
                                CurrencyEvent.Loading
                            is Resource.NoInternet -> _conversionRates.value =
                                CurrencyEvent.ConnectionFailure(response.message!!)
                            is Resource.Error -> _conversionRates.value =
                                CurrencyEvent.Failure(response.message!!)
                            is Resource.Success -> _conversionRates.value =
                                CurrencyEvent.SuccessListResponse(response.data)
                        }
                    }.launchIn(this)
            } }
    }
}