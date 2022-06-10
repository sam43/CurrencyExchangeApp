package com.sam43.currencyexchangeapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {
    @Query("SELECT * FROM currencyrateitem")
    fun getRatesOffline(): Flow<List<CurrencyRateItem>>

    @Query("SELECT * FROM currencyrateitem WHERE country = :country")
    suspend fun getRateByCountry(country: String): CurrencyRateItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRateItem(rateItem: CurrencyRateItem)

}
