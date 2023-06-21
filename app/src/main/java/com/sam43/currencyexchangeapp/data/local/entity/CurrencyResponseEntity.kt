package com.sam43.currencyexchangeapp.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates

@Keep
@Entity(tableName = "rate")
data class CurrencyResponseEntity(
    @PrimaryKey val id: Int? = null,
    val base: String = "USD",
    val rates: Rates,
    val timestamp: Long? = 0L
) {
    fun toRateInfo(): CurrencyResponse = CurrencyResponse(
        base = base, rates = rates, timestamp = timestamp
    )
}

class InvalidRateException(message: String): Exception(message)
