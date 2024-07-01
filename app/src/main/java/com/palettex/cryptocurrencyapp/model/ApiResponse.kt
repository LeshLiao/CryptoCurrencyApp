package com.palettex.cryptocurrencyapp.model

data class ApiResponse(
    val BTC: CurrencyData,
    val ETH: CurrencyData,
    val BNB: CurrencyData,
    val USDT: CurrencyData,
    val ADA: CurrencyData,
    val SOL: CurrencyData,
    val XRP: CurrencyData,
    val DOT: CurrencyData,
    val DOGE: CurrencyData,
    val UNI: CurrencyData
)
