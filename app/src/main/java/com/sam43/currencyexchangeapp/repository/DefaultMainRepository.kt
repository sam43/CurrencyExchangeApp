package com.sam43.currencyexchangeapp.repository

import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Resource
import com.sam43.currencyexchangeapp.utils.to5decimalPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi,
    private val dao: RateDao
) : MainRepository {

    override fun getRatesOffline(base: String): Flow<Resource<CurrencyResponse>> =
        flow {
            emit(Resource.Loading())
            try {
                val remoteRateInfos = base.let { api.getRates(it) }
                remoteRateInfos.body()?.toCurrencyInfoEntity()?.let { dao.insertRateInfos(it) }
            } catch(e: HttpException) {
                emit(Resource.Error(
                    message = "Oops, Some error occurred while parsing the response!"
                ))
            } catch(e: IOException) {
                emit(Resource.NoInternet(
                    message = "Couldn't reach server, check your internet connection."
                ))
            } finally {
                // Satisfying single source of truth
                val rateInfoFinal = dao.getRatesOffline().toRateInfo()
                emit(Resource.Success(data = rateInfoFinal))
            }
        }

    private fun getRatesAsList(rates: Rates, amount: Double? = 1.0): MutableList<CurrencyRateItem> {
        return mutableListOf(CurrencyRateItem(country = "CAD", currency = (amount!! * rates.cAD!!).to5decimalPoint()),
            CurrencyRateItem(country = "HKD", currency = (amount * rates.hKD!!).to5decimalPoint()),
            CurrencyRateItem(country = "ISK", currency = (amount * rates.iSK!!).to5decimalPoint()),
            CurrencyRateItem(country = "BDT", currency = (amount * rates.bDT!!).to5decimalPoint()),
            CurrencyRateItem(country = "EUR", currency = (amount * rates.eUR!!).to5decimalPoint()),
            CurrencyRateItem(country = "PHP", currency = (amount * rates.pHP!!).to5decimalPoint()),
            CurrencyRateItem(country = "DKK", currency = (amount * rates.dKK!!).to5decimalPoint()),
            CurrencyRateItem(country = "HUF", currency = (amount * rates.hUF!!).to5decimalPoint()),
            CurrencyRateItem(country = "CZK", currency = (amount * rates.cZK!!).to5decimalPoint()),
            CurrencyRateItem(country = "AUD", currency = (amount * rates.aUD!!).to5decimalPoint()),
            CurrencyRateItem(country = "RON", currency = (amount * rates.rON!!).to5decimalPoint()),
            CurrencyRateItem(country = "SEK", currency = (amount * rates.sEK!!).to5decimalPoint()))
    }
}