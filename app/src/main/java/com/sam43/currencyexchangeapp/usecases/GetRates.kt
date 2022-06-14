package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.local.entity.InvalidRateException
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.poller.DataPoller
import com.sam43.currencyexchangeapp.network.poller.Poller
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRates @Inject constructor(
    private val repository: MainRepository,
    private val poller: Poller
) {

    @Throws(InvalidRateException::class)
    operator fun invoke(base: String): Flow<Resource<CurrencyResponse?>> {
        if(base.isBlank()) {
            return flow {  }
        }
        return poller.poll(10, base)
    }
}
