package com.sam43.currencyexchangeapp.network

import android.util.Log
import com.sam43.currencyexchangeapp.CurrencyApplication
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import kotlinx.coroutines.*
import javax.inject.Inject

@Deprecated("Currently using kotlin extension for the periodic api call")
class Timer @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: MainRepository,
    private val base: String? = "USD"
) {

    private val TAG = "Timer"

    private val job = SupervisorJob()
    private val scope = CoroutineScope(dispatcherProvider.io + job)

    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 1000, action: () -> Unit) = scope.launch(dispatcherProvider.io) {
        withContext(dispatcherProvider.io) {
            delay(delayMillis)
            if (repeatMillis > 0) {
                while (CurrencyApplication.isNetworkConnected) {
                    action()
                    delay(repeatMillis)
                }
            } else {
                action()
            }
        }
    }

    private val timer: Job = startCoroutineTimer(delayMillis = 60*1000, repeatMillis = 30*60*1000) {
        Log.d(TAG, "Background - tick")
        repository.getRatesOffline(base.toString())
//        scope.launch(dispatcherProvider.main) {
//            Log.d(TAG, "Main thread - tick")
//            repository.getRatesOffline(base.toString())
//        }
    }

    fun startTimer() {
        timer.start()
    }

    fun cancelTimer() {
        timer.cancel()
    }
}