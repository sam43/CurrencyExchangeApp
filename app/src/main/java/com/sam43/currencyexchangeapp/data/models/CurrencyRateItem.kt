package com.sam43.currencyexchangeapp.data.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
data class CurrencyRateItem(
    @PrimaryKey val id: Int? = null,
    val country: String,
    val currency: String
)