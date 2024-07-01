package com.palettex.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.palettex.cryptocurrencyapp.ui.theme.CryptocurrencyappTheme
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.palettex.cryptocurrencyapp.network.RetrofitClient
import com.palettex.cryptocurrencyapp.repository.CryptoRepository
import com.palettex.cryptocurrencyapp.viewmodel.CryptoViewModel
import com.palettex.cryptocurrencyapp.viewmodel.CryptoViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptocurrencyappTheme {
                val cryptoApiService = RetrofitClient.provideCryptoApiService(RetrofitClient.myInstance)
                val repository = CryptoRepository(cryptoApiService)
                val viewModel: CryptoViewModel = viewModel(factory = CryptoViewModelFactory(repository))
                CryptoDataScreen(viewModel)
            }
        }
    }
}

@Composable
fun CryptoDataScreen(viewModel: CryptoViewModel) {
    val cryptoData by viewModel.cryptoData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Crypto Online Price",
            style = TextStyle(fontSize = 24.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (cryptoData.RAW != null) {
            Column {
                Text(text = "FROMSYMBOL: ${cryptoData.RAW!!.FROMSYMBOL}")
                Text(text = "TOSYMBOL: ${cryptoData.RAW!!.TOSYMBOL}")
                Text(text = "PRICE: ${cryptoData.RAW!!.PRICE}")
            }
        } else {
            Text(text = "Loading...")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchCryptoData("BTC", "USD")
    }
}
