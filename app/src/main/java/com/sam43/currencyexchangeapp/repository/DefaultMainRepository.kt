package com.sam43.currencyexchangeapp.repository

import android.util.Log
import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Resource
import com.sam43.currencyexchangeapp.utils.getRatesAsList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dao: RateDao
) : MainRepository {

    override fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse?>> = flow {
        emit(Resource.Loading())
        // Satisfying single source of truth
        val rateInfo = dao.getRatesOffline()?.toRateInfo()
        rateInfo?.let {
            emit(Resource.Loading(data = it))
        }
        try {
            val remoteRateInfo = base.let { api.getRates(it) }
            remoteRateInfo.body()?.toCurrencyInfoEntity()?.let {
                dao.insertRateInfos(it)
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, Some error occurred while parsing the response!",
                    data = rateInfo
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.NoInternet(
                    message = "Couldn't reach server, check your internet connection.",
                    data = rateInfo
                )
            )
        }

        val rateInfoFinal = dao.getRatesOffline()?.toRateInfo()
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
                val rates = dao.getRatesOffline()?.rates
                //val unitRate = rates?.let { getConvertedRate(it, from, to) }
                val rateList = rates?.let { getRatesAsList(it, amountStr.toDouble(), from) }
                Log.d("CONVERT_MONEY", "getConvertedRates() called : ${rateList.toString()}")
                emit(Resource.Success(data = rateList!!))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}