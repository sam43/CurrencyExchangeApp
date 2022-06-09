package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.Resource

interface MainRepository {
    suspend fun getRates(): Resource<CurrencyResponse>
}