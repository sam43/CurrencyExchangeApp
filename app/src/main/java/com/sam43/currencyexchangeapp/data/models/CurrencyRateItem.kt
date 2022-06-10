package com.sam43.currencyexchangeapp.data.models

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class CurrencyRateItem(
    @PrimaryKey var country: String? = null,
    var currency: String? = null
)

class InvalidRateException(message: String): Exception(message)