package com.sam43.currencyexchangeapp.domain.worker

import android.R.id
import androidx.work.*
import com.sam43.currencyexchangeapp.utils.Constants
import java.util.concurrent.TimeUnit

fun WorkManager.fetchData(base: String) {
    // Create Network constraint
    val constraints: Constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val inputData: Data = Data.Builder()
        .putString(Constants.BASE_CURRENCY, base)
        .build()

    val periodicSyncDataWork = PeriodicWorkRequestBuilder<SyncDataWorker>(
        30, TimeUnit.MINUTES, // repeatInterval (the period cycle)
        15, TimeUnit.MINUTES) // flexInterval
        .addTag(Constants.SYNC_LATEST_DATA)
        .setInputData(inputData)
        .setConstraints(constraints) // setting a backoff on case the work needs to retry
        .setBackoffCriteria(
            BackoffPolicy.LINEAR,
            PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.MILLISECONDS
        )
        .build()

    this.enqueueUniquePeriodicWork(
        Constants.SYNC_DATA_FROM_SERVER_WORK,
        ExistingPeriodicWorkPolicy.KEEP,  //Existing Periodic Work policy
        periodicSyncDataWork //work request
    )
}