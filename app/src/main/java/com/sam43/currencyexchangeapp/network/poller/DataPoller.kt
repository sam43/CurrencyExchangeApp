@file:OptIn(ExperimentalCoroutinesApi::class)

package com.sam43.currencyexchangeapp.network.poller

import android.util.Log
import com.sam43.currencyexchangeapp.CurrencyApplication
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class DataPoller @Inject constructor(
    //private val repository: MainRepository,
    private val api: CurrencyApi,
    private val dispatcher: DispatcherProvider
) : Poller {
    private val TAG = "DataPoller"
    override fun poll(delay: Long, query: String): Flow<Response<CurrencyResponseDto>> =
        channelFlow {
            while (!isClosedForSend) {
                if (CurrencyApplication.pollingState == "INACTIVE" || !CurrencyApplication.isInternetConnected) {
                    Log.d(TAG, "poll() stopping called")
                    close()
                    return@channelFlow
                }
                Log.d(TAG, "poll() running called")
                // X unit time delay
                delay(delay)
                // Making api call
                send(api.getRates(base = query))
            }
        }.flowOn(dispatcher.io)
}