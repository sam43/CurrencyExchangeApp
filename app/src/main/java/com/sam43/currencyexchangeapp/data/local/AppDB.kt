package com.sam43.currencyexchangeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem

@Database(
    entities = [CurrencyRateItem::class],
    version = 1, exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract val rateDao: RateDao

    companion object {
        const val DATABASE_NAME = "currency_exchange_db"
    }
}