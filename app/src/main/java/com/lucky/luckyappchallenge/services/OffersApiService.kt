package com.lucky.luckyappchallenge.services

import com.lucky.luckyappchallenge.models.Offer
import io.reactivex.Single
import retrofit2.http.GET

interface OffersApiService {

    //TODO: Documentation

    @GET("api/offers")
    fun getOffers(): Single<Offer>
}