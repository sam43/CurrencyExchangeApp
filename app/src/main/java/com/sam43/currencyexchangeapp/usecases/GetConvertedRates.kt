package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.local.entity.InvalidRateException
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetConvertedRates @Inject constructor(private val repository: MainRepository) {

    @Throws(InvalidRateException::class)
    suspend operator fun invoke(amount: String, base: String): Flow<Resource<MutableList<CurrencyRateItem>>> {
        if(base.isBlank()) {
            return flow {  }
        }
        return repository.getConvertedRates(amount, base)
    }
}