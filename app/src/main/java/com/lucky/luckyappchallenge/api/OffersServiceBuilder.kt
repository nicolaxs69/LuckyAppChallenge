package com.lucky.luckyappchallenge.api

import com.lucky.luckyappchallenge.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object OffersServiceBuilder {
    private val client = OkHttpClient
        .Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(OffersApiService::class.java)

    fun buildOffersAvailableService(): OffersApiService {
        return retrofit
    }

    fun buildOffersDetailService(): OffersApiService {
        return retrofit
    }
}