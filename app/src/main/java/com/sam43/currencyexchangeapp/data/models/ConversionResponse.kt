package com.sam43.currencyexchangeapp.data.models

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ConversionResponse(
    @SerializedName("response")
    val response: Double? = 0.0
)