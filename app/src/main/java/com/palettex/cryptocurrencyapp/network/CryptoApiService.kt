package com.palettex.cryptocurrencyapp.network

import com.palettex.cryptocurrencyapp.model.Crypto
import retrofit2.http.GET

interface CryptoApiService {
    @GET("path_to_your_api_endpoint")
    suspend fun getCryptoPrices(): List<Crypto>
}