package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse?>>

    suspend fun getConvertedRates(
        amountStr: String,
        from: String,
        to: String
    ): Flow<Resource<MutableList<CurrencyRateItem>>>
}