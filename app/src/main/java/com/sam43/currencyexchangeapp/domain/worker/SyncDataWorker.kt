package com.sam43.currencyexchangeapp.domain.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.domain.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.Constants
import com.sam43.currencyexchangeapp.utils.JsonParser
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltWorker
class SyncDataWorker @AssistedInject constructor(@Assisted appContext: Context, @Assisted workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var api: CurrencyApi

    @Inject
    lateinit var parser: JsonParser

    @Inject
    lateinit var dao: RateDao

    override suspend fun doWork(): Result {
        //simulate slow work
        val inputData: String? = inputData.getString(Constants.BASE_CURRENCY)
        // WorkerUtils.makeStatusNotification("Fetching Data", applicationContext);
        val base = if (inputData.isNullOrEmpty()) Constants.USD else inputData.toString()

        Log.i(TAG, "Fetching Data from Remote host")
        return try {
            //  create a call to network
            val remoteRateInfos = base.let { api.getRates(it) }
            if (remoteRateInfos.isSuccessful) {
                try {
                    remoteRateInfos.body()?.toCurrencyInfoEntity()?.let { dao.insertRateInfos(it) }
                    val rateInfo = dao.getRatesOffline()?.toRateInfo()
                    val outputData = Data.Builder()
                        .putString(
                            Constants.OUTPUT_DATA, parser.toJson(
                                rateInfo,
                                object : TypeToken<CurrencyResponse>() {}.type
                            ) ?: ""
                        )
                        .build()
                    Result.success(outputData)
                } catch (e: HttpException) {
                    Result.failure(buildErrorData(e.message, Constants.ERROR_HTTP_EXCEPTION))
                } catch (e: IOException) {
                    Result.failure(buildErrorData(e.message, Constants.ERROR_INTERNET_EXCEPTION))
                }
            } else
                Result.retry()
        } catch (e: Throwable) {
            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error fetching data", e)
            e.printStackTrace()
            Result.failure(buildErrorData(e.message, Constants.ERROR_HTTP_EXCEPTION))
        }
    }

    companion object {
        private val TAG = SyncDataWorker::class.java.simpleName
    }
}

fun buildErrorData(value: String?, exception: String) = Data.Builder()
    .putString(Constants.ERROR_TYPE, exception)
    .putString(Constants.ERROR_DATA, value)
    .build()

data class ErrorOnException(
    @SerializedName(Constants.ERROR_TYPE)
    val type: String,
    @SerializedName(Constants.ERROR_DATA)
    val value: String?
)