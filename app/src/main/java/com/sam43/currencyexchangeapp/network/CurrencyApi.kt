package com.sam43.currencyexchangeapp.network

import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.di.AppModule.APP_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest.json?app_id=$APP_ID")
    suspend fun getRates(): Response<CurrencyResponse>

    // Historic data of 2022-05-24
    @GET("historical/{date}.json?app_id=$APP_ID") // date format : YYYY-MM-DD
    suspend fun getRatesByDate(
        @Path("date") date: String,
    ): Response<CurrencyResponse>

    // Currency exchange using server
    // From doc: The /convert API is offered to save time in integration,
    // but does not imply any difference in accuracy, validity or fitness for purpose from the data that can be obtained with any other API request.
    @GET("convert/{amount}/{from}/{to}?app_id=$APP_ID")
    suspend fun convertCurrency(
        @Path("amount") amount: String,
        @Path("from") from: String,
        @Path("to") to: String
    ): Response<ConversionResponse>

}