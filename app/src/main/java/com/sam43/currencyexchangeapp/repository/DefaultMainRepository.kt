package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.local.AppDB
import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi,
    private val appDB: AppDB
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

    override fun getRatesOffline(): Flow<List<CurrencyRateItem>> =
        appDB.rateDao.getRatesOffline()

    override suspend fun getRateByCountry(country: String): CurrencyRateItem? =
        appDB.rateDao.getRateByCountry(country)

    override suspend fun insertRateItem(rateItem: CurrencyRateItem) =
        appDB.rateDao.insertRateItem(rateItem)
}