package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.dummyRatesTest
import com.sam43.currencyexchangeapp.dummyRatesTestDto
import com.sam43.currencyexchangeapp.utils.Resource
import com.sam43.currencyexchangeapp.utils.getRatesAsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository: MainRepository {
    override fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse?>> = flow {
        val dummyResponse = CurrencyResponse(base, dummyRatesTest(), 1000)
        emit(Resource.Success(dummyResponse))
    }

    override suspend fun getConvertedRates(
        amountStr: String,
        from: String,
        to: String
    ): Flow<Resource<MutableList<CurrencyRateItem>>> = flow {
        // Do nothing
    }
}