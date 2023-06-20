package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Resource
import com.sam43.currencyexchangeapp.utils.getRatesAsList
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
        val rateInfo = dao.getRatesOffline()?.toRateInfo()
        rateInfo?.let {
            emit(Resource.Loading(data = it))
        }
        try {
            val remoteRateInfo = api.getRates()
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
                val rateList = rates?.let { getRatesAsList(it, amountStr.toDouble(), from) }
                emit(Resource.Success(data = rateList!!))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}