package com.palettex.cryptocurrencyapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://api.yourcryptosource.com/"

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideCryptoApiService(retrofit: Retrofit): CryptoApiService {
        return retrofit.create(CryptoApiService::class.java)
    }
}