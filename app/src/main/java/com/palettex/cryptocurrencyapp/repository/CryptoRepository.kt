package com.palettex.cryptocurrencyapp.repository

import com.palettex.cryptocurrencyapp.model.Crypto
import com.palettex.cryptocurrencyapp.network.CryptoApiService

class CryptoRepository(private val apiService: CryptoApiService) {
    suspend fun getCryptoPrices(): List<Crypto> {
        return apiService.getCryptoPrices()
    }
}