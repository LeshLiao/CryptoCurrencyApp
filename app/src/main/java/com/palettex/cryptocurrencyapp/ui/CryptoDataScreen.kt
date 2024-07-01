package com.palettex.cryptocurrencyapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.palettex.cryptocurrencyapp.model.ApiResponse
import com.palettex.cryptocurrencyapp.viewmodel.CryptoViewModel

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

        if (cryptoData != null) {
            Table(cryptoData!!)
        } else {
            Text(text = "Loading...")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchCryptoData("BTC,ETH,BNB,USDT,ADA,SOL,XRP,DOT,DOGE,UNI,USDC,BUSD,XLM,LTC,BCH,DAI,LINK,AAVE,MKR,ATOM", "USD,EUR")
    }
}

@Composable
fun Table(apiResponse: ApiResponse) {
    val data = listOf(
        "BTC" to apiResponse.BTC,
        "ETH" to apiResponse.ETH,
        "BNB" to apiResponse.BNB,
        "USDT" to apiResponse.USDT,
        "ADA" to apiResponse.ADA,
        "SOL" to apiResponse.SOL,
        "XRP" to apiResponse.XRP,
        "DOT" to apiResponse.DOT,
        "DOGE" to apiResponse.DOGE,
        "UNI" to apiResponse.UNI,
        "USDC" to apiResponse.USDC,
        "BUSD" to apiResponse.BUSD,
        "XLM" to apiResponse.XLM,
        "LTC" to apiResponse.LTC,
        "BCH" to apiResponse.BCH,
        "DAI" to apiResponse.DAI,
        "LINK" to apiResponse.LINK,
        "AAVE" to apiResponse.AAVE,
        "MKR" to apiResponse.MKR,
        "ATOM" to apiResponse.ATOM
    )

    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "TYPE", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "USD", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "EUR", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(data) { (symbol, currencyData) ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = symbol, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                    Text(text = currencyData.USD.toString(), modifier = Modifier.weight(1f))
                    Text(text = currencyData.EUR.toString(), modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

