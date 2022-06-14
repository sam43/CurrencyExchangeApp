@file:OptIn(ExperimentalCoroutinesApi::class)

package com.sam43.currencyexchangeapp.network.poller

import android.util.Log
import com.sam43.currencyexchangeapp.CurrencyApplication
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

@Deprecated("Not in use currently")
class DataPoller @Inject constructor(
    private val api: CurrencyApi,
    private val dispatcher: DispatcherProvider
) : Poller {
    private val TAG = "DataPoller"
    override fun poll(delay: Long, query: String): Flow<Response<CurrencyResponseDto>> =
        channelFlow {
            while (!isClosedForSend) {
//                if (CurrencyApplication.pollingState == "INACTIVE" || !CurrencyApplication.isInternetConnected) {
//                    Log.d(TAG, "poll() stopping called")
//                    close()
//                    return@channelFlow
//                }
                Log.d(TAG, "poll() running called")
                // X unit time delay
                delay(delay)
                // Making api call
                send(api.getRates(base = query))
            }
        }.flowOn(dispatcher.io)

    override fun pollStop() {
        dispatcher.io.cancel()
    }
}