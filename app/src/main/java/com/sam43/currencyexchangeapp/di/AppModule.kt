package com.sam43.currencyexchangeapp.di

import android.app.Application
import androidx.room.Room
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.sam43.currencyexchangeapp.BuildConfig
import com.sam43.currencyexchangeapp.data.local.AppDB
import com.sam43.currencyexchangeapp.network.CurrencyApi
import com.sam43.currencyexchangeapp.repository.DefaultMainRepository
import com.sam43.currencyexchangeapp.repository.MainRepository
import com.sam43.currencyexchangeapp.usecases.AddRateItem
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.usecases.GetRateItemByCountry
import com.sam43.currencyexchangeapp.usecases.GetRates
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideNoteDatabase(app: Application): AppDB {
        return Room.databaseBuilder(
            app,
            AppDB::class.java,
            AppDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: MainRepository): ConversionUseCases {
        return ConversionUseCases(
            getRates = GetRates(repository),
            addRate = AddRateItem(repository),
            getRateByCountry = GetRateItemByCountry(repository)
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
    fun provideMainRepository(api: CurrencyApi, db: AppDB): MainRepository = DefaultMainRepository(api, db)

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
}