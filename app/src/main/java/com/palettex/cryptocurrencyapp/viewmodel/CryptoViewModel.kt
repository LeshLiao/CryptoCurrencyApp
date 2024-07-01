package com.palettex.cryptocurrencyapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.model.Raw
import com.palettex.cryptocurrencyapp.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {
    private val _cryptoData = MutableStateFlow(ApiResponse(Raw("","",0.0)))
    val cryptoData: StateFlow<ApiResponse> get() = _cryptoData

    fun fetchCryptoData(fromSymbol: String, toSymbol: String) {
        viewModelScope.launch {
            try {
                val prices = repository.getCryptoPrices(fromSymbol, toSymbol)
                _cryptoData.value = prices
            } catch (e: Exception) {
                e.printStackTrace() // Handle the exception
            }
        }
    }
}
