package com.sam43.currencyexchangeapp.network.poller

import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Poller {
    fun poll(delay: Long, query: String): Flow<Response<CurrencyResponseDto>>
    //fun poll2(other : Flow<Resource<CurrencyResponse?>>): Flow<Resource<CurrencyResponse?>>
}