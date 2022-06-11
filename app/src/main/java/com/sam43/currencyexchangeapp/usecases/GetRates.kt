package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRates @Inject constructor(private val repository: MainRepository) {
//    suspend operator fun invoke(base: String? = "USD"): Flow<List<CurrencyRateItem>> {
//        return repository.getRates(base).map {
//
//        }
//    }
}
