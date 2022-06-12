package com.sam43.currencyexchangeapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.room.Room
import androidx.work.WorkManager
import com.google.gson.Gson
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.sam43.currencyexchangeapp.BuildConfig
import com.sam43.currencyexchangeapp.data.local.AppDB
import com.sam43.currencyexchangeapp.data.local.RateDao
import com.sam43.currencyexchangeapp.data.local.entity.Converters
import com.sam43.currencyexchangeapp.domain.network.CurrencyApi
import com.sam43.currencyexchangeapp.domain.repository.DefaultMainRepository
import com.sam43.currencyexchangeapp.domain.repository.MainRepository
import com.sam43.currencyexchangeapp.domain.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.domain.usecases.GetConvertedRates
import com.sam43.currencyexchangeapp.domain.usecases.GetRates
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.GsonParser
import com.sam43.currencyexchangeapp.utils.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDB {
        return Room.databaseBuilder(
            app,
            AppDB::class.java,
            AppDB.DATABASE_NAME
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideRateDao(appDB: AppDB) = appDB.rateDao

    @Provides
    @Singleton
    fun provideWorkManager(app: Application) = WorkManager.getInstance(app)

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: MainRepository): ConversionUseCases {
        return ConversionUseCases(
            getRates = GetRates(repository),
            getConvertedRates = GetConvertedRates(repository)
        )
    }

    @Singleton
    @Provides
    fun provideCurrencyApi(okHttpClient: OkHttpClient): CurrencyApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(CurrencyApi::class.java)


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        okHttpProfilerInterceptor: OkHttpProfilerInterceptor,
        headerInterceptor: Interceptor,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(30L, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30L, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(30L, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            okHttpClientBuilder.addInterceptor(okHttpProfilerInterceptor)
        }
        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder
                .addHeader("Authorization", "Token ${BuildConfig.APP_ID}")
                .build())
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpProfilerInterceptor(): OkHttpProfilerInterceptor = OkHttpProfilerInterceptor()


    @Singleton
    @Provides
    fun provideMainRepository(dao: RateDao, workManager: WorkManager): MainRepository =
        DefaultMainRepository(dao, workManager)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    @Provides
    @Singleton
    fun provideJsonParser(parser: GsonParser): JsonParser = parser

    @Provides
    @Singleton
    fun provideCheckForInternet(@ApplicationContext context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}