package com.sam43.currencyexchangeapp.network

import com.sam43.currencyexchangeapp.BuildConfig
import com.sam43.currencyexchangeapp.data.models.ConversionResponse
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest.json")
    suspend fun getRates(
        @Query("base") base: String = ApiConstants.DEFAULT_CURRENCY
    ): Response<CurrencyResponseDto>

    // Currency exchange using server
    // From doc: The /consumeAllRatesByBase API is offered to save time in integration,
    // but does not imply any difference in accuracy, validity or fitness for purpose from the data that can be obtained with any other API request.
//    @GET("convert/{value}/{from}/{to}")
//    suspend fun convertCurrency(
//        @Path("value") amount: String,
//        @Path("from") from: String,
//        @Path("to") to: String
//    ): Response<ConversionResponse>

}