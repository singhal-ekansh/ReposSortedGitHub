package com.advanced.githubrepos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    private const val base_url: String = "https://api.github.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}