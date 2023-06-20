package com.sam43.currencyexchangeapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
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
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent {
        class SuccessResponse(val response: CurrencyResponse?) : CurrencyEvent()
        class SuccessListResponse<T>(val list: MutableList<T>?) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Loading)
    val conversion: StateFlow<CurrencyEvent> = _conversion
    private val _conversionRates = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Loading)
    val conversionRates: StateFlow<CurrencyEvent> = _conversionRates

    fun consumeRatesApi(base: String? = AppConstants.DEFAULT_CURRENCY) {
        _conversion.value = CurrencyEvent.Loading
        viewModelScope.launch(dispatchers.io) {
            base?.let {
                useCases.getRates(base)
                    .onEach { response ->
                        when (response) {
                            is Resource.Loading -> _conversion.value =
                                CurrencyEvent.Loading
                            is Resource.Error -> _conversion.value =
                                CurrencyEvent.Failure(response.message.toString())
                            is Resource.Success -> _conversion.value =
                                CurrencyEvent.SuccessResponse(response.data)
                        }
                    }.launchIn(this)
            }
        }
    }

    fun convert(
        amountStr: String?,
        from: String? = AppConstants.DEFAULT_CURRENCY,
        to: String?
    ) {
        val fromAmount = amountStr?.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure(AppConstants.INVALID_AMOUNT)
            return
        }

        viewModelScope.launch(dispatchers.io) {
            from?.let {
                useCases.getConvertedRates(amount = amountStr, from = it, to = to.toString())
                    .onEach { response ->
                        when (response) {
                            is Resource.Loading -> _conversionRates.value =
                                CurrencyEvent.Loading
                            is Resource.Error -> _conversionRates.value =
                                CurrencyEvent.Failure(response.message!!)
                            is Resource.Success -> _conversionRates.value =
                                CurrencyEvent.SuccessListResponse(response.data)
                        }
                    }.launchIn(this)
            }
        }
    }
}