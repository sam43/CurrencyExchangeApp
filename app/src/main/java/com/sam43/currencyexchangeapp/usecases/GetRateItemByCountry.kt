package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.repository.MainRepository
import javax.inject.Inject

class GetRateItemByCountry @Inject constructor(private val repository: MainRepository) {

}