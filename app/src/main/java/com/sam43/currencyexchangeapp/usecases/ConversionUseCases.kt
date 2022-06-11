package com.sam43.currencyexchangeapp.usecases

data class ConversionUseCases(
    val getRates: GetRates,
    val addRate: AddRateItem,
    val getRateByCountry: GetRateItemByCountry
)
