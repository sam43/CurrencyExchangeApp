package com.sam43.currencyexchangeapp.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sam43.currencyexchangeapp.data.local.dao.RateDao
import com.sam43.currencyexchangeapp.data.local.entity.Converters
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity

@Database(
    entities = [CurrencyResponseEntity::class],
    version = 2, exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)

@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {
    abstract val rateDao: RateDao

    companion object {
        const val DATABASE_NAME = "currency_exchange_db"
    }
}