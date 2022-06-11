package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse>>
}