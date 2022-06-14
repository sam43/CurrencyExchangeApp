package com.sam43.currencyexchangeapp.network.poller

import android.util.Log
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import kotlinx.coroutines.*
import javax.inject.Inject

class Timer @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: MainRepository,
    private val base: String? = "USD"
) {

    private val TAG = "Timer"

    private val job = SupervisorJob()
    private val scope = CoroutineScope(dispatcherProvider.default + job)

    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 1000, action: () -> Unit) = scope.launch(dispatcherProvider.io) {
        withContext(dispatcherProvider.io) {
            delay(delayMillis)
            if (repeatMillis > 0) {
                while (true) {
                    action()
                    delay(repeatMillis)
                }
            } else {
                action()
            }
        }
    }

    private val timer: Job = startCoroutineTimer(delayMillis = 0, repeatMillis = 1800*1000) {
        Log.d(TAG, "Background - tick")
        repository.getRatesOffline(base.toString())
//        scope.launch(Dispatchers.Main) {
//            Log.d(TAG, "Main thread - tick")
//            doSomethingMainThread()
//        }
    }

    fun startTimer() {
        timer.start()
    }

    fun cancelTimer() {
        timer.cancel()
    }
}