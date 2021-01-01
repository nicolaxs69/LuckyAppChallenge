package com.lucky.luckyappchallenge.services

import com.lucky.luckyappchallenge.models.Offer
import com.lucky.luckyappchallenge.models.OfferDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface OffersApiService {

    @GET("api/offers")
    fun getOffers(): Single<Offer>

    @GET("api/offers/{productId}")
    fun getOffersDetail(
        @Path("productId") productId: String
    ): Single<OfferDetail>
}