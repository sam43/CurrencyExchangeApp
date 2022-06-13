package com.sam43.currencyexchangeapp.domain.usecases

import com.sam43.currencyexchangeapp.data.local.entity.InvalidRateException
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.domain.repository.MainRepository
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRates @Inject constructor(private val repository: MainRepository) {

    @Throws(InvalidRateException::class)
    operator fun invoke(workResult: String): Flow<Resource<CurrencyResponse?>> {
        if(workResult.isBlank()) {
            return flow {  }
        }
        return repository.getRatesOffline(workResult)
    }
}
