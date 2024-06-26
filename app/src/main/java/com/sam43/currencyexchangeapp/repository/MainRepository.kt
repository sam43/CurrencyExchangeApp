package com.sam43.currencyexchangeapp.repository

import android.util.Log
import com.sam43.currencyexchangeapp.data.local.dao.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.Resource
import com.sam43.currencyexchangeapp.utils.asMap
import com.sam43.currencyexchangeapp.utils.fetchRatesAsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dao: RateDao
) : IMainRepository {

    override fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse?>> = flow {
        emit(Resource.Loading())
        // Satisfying single source of truth
        val rateInfo = dao.fetchRatesFromDB()?.toRateInfo()
        rateInfo?.let {
            emit(Resource.Success(data = it))
        }
        try {
            val remoteRateInfo = api.getRates()
            Log.d("IMainRepository", "getRatesOffline: ${remoteRateInfo.body()?.rates}")
            remoteRateInfo.body()?.toCurrencyInfoEntity()?.let {
                dao.insertRateInfo(it)
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = AppConstants.HTTP_EXCEPTION_ERROR,
                    data = rateInfo
                )
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = e.message.toString(),
                    data = rateInfo
                )
            )
        }

        val rateInfoFinal = dao.fetchRatesFromDB()?.toRateInfo()
        emit(Resource.Success(data = rateInfoFinal))
    }

    override suspend fun getConvertedRates(
        amountStr: String,
        from: String,
        to: String
    ): Flow<Resource<MutableList<CurrencyRateItem>>> =
        flow {
            emit(Resource.Loading())
            try {
                val rates = dao.fetchRatesFromDB()?.rates
                val rateList =
                    rates?.let { fetchRatesAsList(it.asMap(), it, amountStr.toDouble(), from) } ?: mutableListOf()
                emit(Resource.Success(data = rateList))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}