package com.palettex.cryptocurrencyapp.repository

import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.network.ApiService

class CryptoRepository(private val apiService: ApiService) {
    suspend fun getCryptoPrices(fromSymbols: String, toSymbols: String): ApiResponse {
        return apiService.getCryptoData(fromSymbols, toSymbols)
    }
}