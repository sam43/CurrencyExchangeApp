package com.sam43.currencyexchangeapp.data.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity

@Keep
data class CurrencyResponseDto(
    @SerializedName("base")
    val base: String? = "",
    @SerializedName("rates")
    val rates: RatesDto? = RatesDto(),
    @SerializedName("timestamp")
    val timestamp: Int? = 0
) {
    fun toCurrencyInfoEntity(): CurrencyResponseEntity {
        return CurrencyResponseEntity(
            base = base, rates = rates?.toRateInfo(), timestamp = timestamp?.toLong()
        )
    }
}