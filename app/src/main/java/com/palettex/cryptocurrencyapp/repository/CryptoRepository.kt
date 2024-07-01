package com.palettex.cryptocurrencyapp.repository

import com.palettex.cryptocurrencyapp.model.Crypto
import com.palettex.cryptocurrencyapp.network.CryptoApiService

class CryptoRepository(private val apiService: CryptoApiService) {
    suspend fun getCryptoPrices(): List<Crypto> {
        return apiService.getCryptoPrices()
        // Create a list of mock crypto data
//        val mockCryptoList = listOf(
//            Crypto("BTC", "135000.00"),
//            Crypto("ETH", "22500.00"),
//            Crypto("ADA", "3.50"),
//            Crypto("DOGE", "4.30"),
//            Crypto("MyFakeCoin", "5123.45") // Your custom mock crypto data
//        )
//        return mockCryptoList
    }
}