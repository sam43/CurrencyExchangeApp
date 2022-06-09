package com.sam43.currencyexchangeapp.data.models

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CurrencyResponse(
    @SerializedName("base")
    val base: String? = "",
    @SerializedName("rates")
    val rates: Rates? = Rates(),
    @SerializedName("timestamp")
    val timestamp: Int? = 0
)