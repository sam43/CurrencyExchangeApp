package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.Resource

interface MainRepository {
    suspend fun getRates(): Resource<CurrencyResponse>
    suspend fun getConvertedRates(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ): Resource<ConversionResponse>
}