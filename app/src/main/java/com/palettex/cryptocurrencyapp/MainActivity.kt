package com.palettex.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.palettex.cryptocurrencyapp.network.NetworkModule
import com.palettex.cryptocurrencyapp.repository.CryptoRepository
import com.palettex.cryptocurrencyapp.ui.theme.CryptocurrencyappTheme
import com.palettex.cryptocurrencyapp.viewmodel.CryptoViewModel
import com.palettex.cryptocurrencyapp.viewmodel.CryptoViewModelFactory
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.palettex.cryptocurrencyapp.model.Crypto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrencyappTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                val cryptoApiService = NetworkModule.provideCryptoApiService(NetworkModule.provideRetrofit())
                val repository = CryptoRepository(cryptoApiService)
                val viewModel: CryptoViewModel = viewModel(factory = CryptoViewModelFactory(repository))
                CryptoScreen(viewModel)
            }
        }
    }
}

@Composable
fun CryptoScreen(viewModel: CryptoViewModel) {
    val cryptoPrices = viewModel.cryptoPrices.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCryptoPrices()
    }

    // Create a list of mock crypto data
//    val mockCryptoList = listOf(
//        Crypto("BTC", "35000.00"),
//        Crypto("ETH", "2500.00"),
//        Crypto("ADA", "1.50"),
//        Crypto("DOGE", "0.30"),
//        Crypto("MyFakeCoin", "123.45") // Your custom mock crypto data
//    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(cryptoPrices.value) { crypto ->
            CryptoItem(crypto)
        }
//        items(mockCryptoList) { crypto ->
//            CryptoItem(crypto)
//        }
    }
}

@Composable
fun CryptoItem(crypto: Crypto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
//        Text(text = "Symbol: ${crypto.symbol}", style = MaterialTheme.typography.bodySmall)
//        Text(text = "Price: ${crypto.price}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Symbol: ${crypto.username}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Price: ${crypto.phone}", style = MaterialTheme.typography.bodySmall)
    }
}
