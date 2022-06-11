package com.sam43.currencyexchangeapp.data.models

import androidx.annotation.Keep

@Keep
data class CurrencyResponse(
    val base: String? = "USD",
    val rates: Rates? = Rates(),
    val timestamp: Long? = 0L
)