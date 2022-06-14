package com.sam43.currencyexchangeapp.network.poller

import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Poller {
    fun poll(delay: Long, query: String): Flow<Resource<CurrencyResponse?>>
}