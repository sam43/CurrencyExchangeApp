package com.sam43.currencyexchangeapp.data.remote


import androidx.annotation.Keep
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.utils.toRateInfoObject

@Keep
data class CurrencyResponseDto(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: HashMap<String,String>,
    val timestamp: Double
) {
    fun toCurrencyInfoEntity(): CurrencyResponseEntity {
        return CurrencyResponseEntity(
            base = base, rates = rates.toRateInfoObject(), timestamp = timestamp.toLong()
        )
    }
}
