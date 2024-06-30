package com.palettex.cryptocurrencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palettex.cryptocurrencyapp.model.Crypto
import com.palettex.cryptocurrencyapp.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {
    private val _cryptoPrices = MutableStateFlow<List<Crypto>>(emptyList())
    val cryptoPrices: StateFlow<List<Crypto>> get() = _cryptoPrices

    fun fetchCryptoPrices() {
        viewModelScope.launch {
            val prices = repository.getCryptoPrices()
            _cryptoPrices.value = prices
        }
    }
}
