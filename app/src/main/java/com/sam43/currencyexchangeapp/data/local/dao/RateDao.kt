package com.sam43.currencyexchangeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateInfo(info: CurrencyResponseEntity)

    @Query("SELECT * FROM rate")
    fun fetchRatesFromDB(): CurrencyResponseEntity?

}