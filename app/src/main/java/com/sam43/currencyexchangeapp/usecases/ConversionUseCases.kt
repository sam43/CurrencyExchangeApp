package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem

data class ConversionUseCases(
    val getRates: GetRates,
    val addRate: AddRateItem,
    val getRateByCountry: GetRateItemByCountry
)
