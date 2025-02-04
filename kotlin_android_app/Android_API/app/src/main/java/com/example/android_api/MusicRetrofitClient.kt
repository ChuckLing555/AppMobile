package com.example.android_api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constant{
    const val BASE_URL = "https://67288c71270bd0b975561f2b.mockapi.io/0306221433/LeThiTrucLinh/"
}

object MusicRetrofitClient{
    val musicAPIService : MusicAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory
                .create(GsonBuilder().create()))
            .build()
            .create(MusicAPIService::class.java)
    }
}