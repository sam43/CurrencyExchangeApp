package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.BuildConfig
import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {

    override suspend fun getRates(base: String?): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base.toString())
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }

    override suspend fun getConvertedRates(
        amountStr: String,
        fromCurrency: String,
        toCurrency: String
    ): Resource<ConversionResponse> {
        return try {
            val response = api.convertCurrency(amountStr, fromCurrency, toCurrency)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
}