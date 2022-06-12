package com.sam43.currencyexchangeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateInfos(infos: CurrencyResponseEntity)

    @Query("SELECT * FROM rate")
    suspend fun getRatesOffline(): CurrencyResponseEntity?

}
