package com.palettex.cryptocurrencyapp.network

import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.model.Raw
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/pricemulti")
    suspend fun getCryptoData(
        @Query("fsyms") fromSymbols: String,
        @Query("tsyms") toSymbols: String
    ): ApiResponse
}