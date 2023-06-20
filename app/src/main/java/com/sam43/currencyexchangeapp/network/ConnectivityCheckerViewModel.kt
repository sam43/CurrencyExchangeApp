package com.sam43.currencyexchangeapp.network

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectivityCheckerViewModel @Inject constructor(
    networkStatusTracker: NetworkStatusTracker
): ViewModel() {
    val connectivityState = networkStatusTracker.networkStatus.map(
        onAvailable = { ConnectivityState.ConnectionAvailable },
        onUnavailable = { ConnectivityState.ConnectionUnavailable },
    )
}

sealed class ConnectivityState {
    object ConnectionAvailable: ConnectivityState()
    object ConnectionUnavailable: ConnectivityState()
}