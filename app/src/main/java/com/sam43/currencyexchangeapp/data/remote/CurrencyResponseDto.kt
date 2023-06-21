package com.sam43.currencyexchangeapp.data.remote


import androidx.annotation.Keep
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity

@Keep
data class CurrencyResponseDto(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: RatesDto,
    val timestamp: Int
) {
    fun toCurrencyInfoEntity(): CurrencyResponseEntity {
        return CurrencyResponseEntity(
            base = base, rates = rates.toRateInfo(), timestamp = timestamp.toLong()
        )
    }
}