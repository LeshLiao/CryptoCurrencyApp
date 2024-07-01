package com.palettex.cryptocurrencyapp.network

import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.model.Raw
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/generateAvg")
    suspend fun getCryptoData(
        @Query("fsym") fromSymbol: String,
        @Query("tsym") toSymbol: String,
        @Query("e") exchange: String = "coinbase" // Default to coinbase
    ): ApiResponse
}