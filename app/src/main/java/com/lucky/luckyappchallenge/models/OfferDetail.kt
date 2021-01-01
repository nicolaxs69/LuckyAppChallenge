package com.lucky.luckyappchallenge.models

import com.google.gson.annotations.SerializedName

data class OfferDetail(
    @SerializedName("brand") val brand: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("expiration") val expiration: String = "",
    @SerializedName("favoriteCount") val favoriteCount: Int = 0,
    @SerializedName("id") val id: Int = 0,
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("price") val price: Price,
    @SerializedName("redemptionsCap") val redemptionsCap: String = "",
    @SerializedName("tags") val tags: String = ""
)

data class Price(
    @SerializedName("new") val new: String = "",
    @SerializedName("old") val old: String = ""
)