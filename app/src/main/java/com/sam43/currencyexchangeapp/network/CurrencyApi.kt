package com.sam43.currencyexchangeapp.network

import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest.json")
    suspend fun getRates(
        @Query("app_id") appId: String
    ): Response<CurrencyResponse>

    // Historic data of 2022-05-24
    @GET("historical/{date}.json") // date format : YYYY-MM-DD
    suspend fun getRatesByDate(
        @Path("date") date: String,
        @Query("app_id") appId: String
    ): Response<CurrencyResponse>

    // Currency exchange using server
    // From doc: The /convert API is offered to save time in integration,
    // but does not imply any difference in accuracy, validity or fitness for purpose from the data that can be obtained with any other API request.
    @GET("convert/{amount}/{from}/{to}")
    suspend fun convertCurrency(
        @Path("amount") amount: String,
        @Path("from") from: String,
        @Path("to") to: String,
        @Query("app_id") appId: String
    ): Response<ConversionResponse>

}