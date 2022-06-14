package com.sam43.currencyexchangeapp.network.poller

import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Deprecated("Not in use currently")
interface Poller {
    fun poll(delay: Long, query: String): Flow<Response<CurrencyResponseDto>>
    fun pollStop()
}