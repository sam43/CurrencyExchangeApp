package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.local.entity.CurrencyResponseEntity
import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getRates(base: String? = "USD"): Resource<CurrencyResponseDto>
//    suspend fun getConvertedRates(
//        amountStr: String,
//        fromCurrency: String,
//        toCurrency: String
//    ): Resource<ConversionResponse>

    fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse>>
//
//    suspend fun getRateByCountry(country: String): CurrencyRateItem?

    //suspend fun insertRateItem(rateItem: CurrencyRateItem)
}