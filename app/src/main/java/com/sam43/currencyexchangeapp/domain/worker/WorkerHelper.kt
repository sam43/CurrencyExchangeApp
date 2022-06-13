package com.sam43.currencyexchangeapp.domain.worker

import androidx.work.*
import com.sam43.currencyexchangeapp.utils.Constants
import java.util.concurrent.TimeUnit


object WorkerHelper {
    private var syncWorkRequest: PeriodicWorkRequest? = null

    fun WorkManager.fetchData(base: String) {
        // Create Network constraint
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData: Data = Data.Builder()
            .putString(Constants.BASE_CURRENCY, base)
            .build()

//        val periodicSyncDataWork = PeriodicWorkRequestBuilder<SyncDataWorker>(
//            5, TimeUnit.SECONDS, // repeatInterval (the period cycle)
//            3, TimeUnit.SECONDS) // flexInterval
//            .build()

        val workBuilder =
            PeriodicWorkRequest.Builder(SyncDataWorker::class.java, 10, TimeUnit.SECONDS)
        val periodicWorkRequest = workBuilder
            .addTag(Constants.SYNC_DATA_FROM_SERVER_WORK)
            .setInputData(inputData)
            .setConstraints(constraints) // setting a backoff on case the work needs to retry
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        setSyncWorkRequest(periodicWorkRequest)
//        enqueueUniquePeriodicWork(
//            Constants.SYNC_DATA_FROM_SERVER_WORK,
//            ExistingPeriodicWorkPolicy.REPLACE,  //Existing Periodic Work policy
//            periodicSyncDataWork //work request
//        )

        enqueueUniquePeriodicWork(
            Constants.SYNC_DATA_FROM_SERVER_WORK,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest
        )
    }

    private fun setSyncWorkRequest(periodicSyncDataWork: PeriodicWorkRequest) {
        syncWorkRequest = periodicSyncDataWork
    }

    fun getSyncWorkRequest() = syncWorkRequest
}
