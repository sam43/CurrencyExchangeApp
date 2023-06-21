package com.sam43.currencyexchangeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sam43.currencyexchangeapp.data.local.entity.Converters
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.remote.RatesDto

@Database(
    entities = [CurrencyResponseEntity::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {
    abstract val rateDao: RateDao

    companion object {
        const val DATABASE_NAME = "currency_exchange_db"
    }
}