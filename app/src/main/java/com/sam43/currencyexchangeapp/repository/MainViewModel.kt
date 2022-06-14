package com.sam43.currencyexchangeapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.tickerFlow
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: ConversionUseCases,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    sealed class CurrencyEvent {
        class SuccessResponse(val response: CurrencyResponse?): CurrencyEvent()
        class SuccessListResponse<T>(val list: MutableList<T>?): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        class ConnectionFailure(val errorText: String): CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion
    private val _conversionRates = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversionRates: StateFlow<CurrencyEvent> = _conversionRates

    fun consumeRatesApi(base: String? = "USD") {
        _conversion.value = CurrencyEvent.Loading
        viewModelScope.launch(dispatchers.io) {
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