package com.sam43.currencyexchangeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sam43.currencyexchangeapp.data.models.Rates

@Database(
    entities = [Rates::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract val rateDao: RateDao

    companion object {
        const val DATABASE_NAME = "currency_exchange_db"
    }
}