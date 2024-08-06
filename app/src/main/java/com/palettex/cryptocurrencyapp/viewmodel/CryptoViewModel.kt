package com.palettex.cryptocurrencyapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.model.CartItem
import com.palettex.cryptocurrencyapp.model.Raw
import com.palettex.cryptocurrencyapp.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CryptoViewModel(private val repository: CryptoRepository) : ViewModel() {
    private val _cryptoData = MutableStateFlow<ApiResponse?>(null)
    val cryptoData: StateFlow<ApiResponse?> get() = _cryptoData

    fun fetchCryptoData(fromSymbols: String, toSymbols: String) {
        viewModelScope.launch {
            try {
                val prices = repository.getCryptoPrices(fromSymbols, toSymbols)
                _cryptoData.value = prices
            } catch (e: Exception) {
                e.printStackTrace() // Handle the exception
            }
        }
    }



    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems


    fun addItem(id: Int) {
        val existingItem = _cartItems.value?.find { it.id == id }
        if (existingItem != null) {
            updateQuantity(id, existingItem.quantity + 1)
        } else {
            _cartItems.value = _cartItems.value.orEmpty() + CartItem(id, 1)
        }
    }

    fun removeItem(id: Int) {
        _cartItems.value = _cartItems.value?.filter { it.id != id }
    }

    fun updateQuantity(id: Int, newQuantity: Int) {
        val existingItem = _cartItems.value?.find { it.id == id }
        if (existingItem != null) {
            if (newQuantity == 0) {
                removeItem(id)
            } else {
                _cartItems.value = _cartItems.value?.map {
                    if (it.id == id) it.copy(quantity = newQuantity) else it
                }
            }
        } else {
            _cartItems.value = _cartItems.value.orEmpty() + CartItem(id, 1)
        }
        Log.d("LeviTest", "id:" + id + "newQuantity:" + newQuantity)
    }

    fun getQuantityById(id: Int): Int {
        return _cartItems.value?.find { it.id == id }?.quantity ?: 0
    }
}
