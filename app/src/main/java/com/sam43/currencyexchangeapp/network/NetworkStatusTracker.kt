package com.sam43.currencyexchangeapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetworkStatusTracker @Inject constructor(
    @ApplicationContext context: Context
) {
    private val TAG = "NetworkStatusTracker.kt"
    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }

    val networkStatus = callbackFlow {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkStatus.Unavailable)
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Unavailable)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                Log.d(TAG, "onCapabilitiesChanged: ${network}, $networkCapabilities")
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                Log.d(TAG, "onLosing")
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager?.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager?.unregisterNetworkCallback(networkStatusCallback)
        }
    }

//    fun isNetworkConnected(): Boolean {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ({
//            connectivityManager?.activeNetwork
//                ?: false
//        }) as Boolean else ({
//            connectivityManager?.activeNetworkInfo?.isConnected
//        }) == true
//    }

//    private fun connectionType(): ConnectionType {
//        var result = ConnectionType.NOT_CONNECTED
//        connectivityManager?.run {
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
//                result = if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) "WIFI"
//                else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) "MOBILE"
//                else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)) VPN else "NOT_CONNECTED"
//            }
//        }
//        return result
//    }

}

inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable: suspend () -> Result,
    crossinline onAvailable: suspend () -> Result,
): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus.Unavailable -> onUnavailable()
        NetworkStatus.Available -> onAvailable()
    }
}

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}