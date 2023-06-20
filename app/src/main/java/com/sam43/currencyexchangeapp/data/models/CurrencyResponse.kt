package com.sam43.currencyexchangeapp.data.models

import androidx.annotation.Keep
import com.sam43.currencyexchangeapp.utils.AppConstants

@Keep
data class CurrencyResponse(
    val base: String? = AppConstants.DEFAULT_CURRENCY,
    val rates: Rates? = Rates(),
    val timestamp: Long? = 0L
)