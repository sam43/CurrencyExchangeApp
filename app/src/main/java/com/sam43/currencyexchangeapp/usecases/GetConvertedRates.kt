package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.local.entity.InvalidRateException
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.repository.IMainRepository
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetConvertedRates @Inject constructor(
    private val repository: IMainRepository,
    ) {

    @Throws(InvalidRateException::class)
    suspend operator fun invoke(amount: String, from: String, to: String): Flow<Resource<MutableList<CurrencyRateItem>>> {
        if(from.isBlank()) {
            return flow {  }
        }
        return repository.getConvertedRates(amount, from, to)
    }
}