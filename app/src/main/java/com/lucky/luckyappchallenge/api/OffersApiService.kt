package com.lucky.luckyappchallenge.api

import com.lucky.luckyappchallenge.data.models.Offer
import com.lucky.luckyappchallenge.data.models.OfferDetail
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