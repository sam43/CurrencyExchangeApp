package com.sam43.currencyexchangeapp.domain.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.google.gson.reflect.TypeToken
import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.domain.network.CurrencyApi
import com.sam43.currencyexchangeapp.domain.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.Constants
import com.sam43.currencyexchangeapp.utils.JsonParser
import com.sam43.currencyexchangeapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class SyncDataWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var api: CurrencyApi

    @Inject
    lateinit var parser: JsonParser

    @Inject
    lateinit var dao: RateDao

    override suspend fun doWork(): Result {
        //simulate slow work
        val base: String? = inputData.getString(Constants.BASE_CURRENCY)
        // WorkerUtils.makeStatusNotification("Fetching Data", applicationContext);
        Log.i(TAG, "Fetching Data from Remote host")
        return try {
            //  create a call to network
            val remoteRateInfos = base?.let { api.getRates(it) }
            if (remoteRateInfos?.isSuccessful == true) {
                remoteRateInfos.body()?.toCurrencyInfoEntity()?.let { dao.insertRateInfos(it) }
                val rateInfo = dao.getRatesOffline()?.toRateInfo()
                val outputData = Data.Builder()
                    .putString(
                        Constants.OUTPUT_DATA, parser.toJson(
                        rateInfo,
                        object : TypeToken<CurrencyResponse>(){}.type
                    ) ?: "")
                    .build()
                Result.success(outputData)
            } else Result.retry()
        } catch (e: Throwable) {
            e.printStackTrace()
            val errorData = Data.Builder()
                .putString(Constants.ERROR_DATA, e.message)
                .build()
            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error fetching data", e)
            Result.failure()
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    companion object {
        private val TAG = SyncDataWorker::class.java.simpleName
    }
}