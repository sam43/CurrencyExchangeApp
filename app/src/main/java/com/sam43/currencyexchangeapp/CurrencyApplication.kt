package com.sam43.currencyexchangeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.random.Random

@HiltAndroidApp
class CurrencyApplication : Application() {
    companion object {
        var isInternetConnected = true // Random.nextBoolean()
        var pollingState = "INACTIVE" // "ACTIVE" //
    }
}