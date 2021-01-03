package com.lucky.luckyappchallenge.data.models

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("brand") val brand: String = "",
    @SerializedName("detailUrl") val detail_url: String = "",
    @SerializedName("favoriteCount") val favorite_count: Int = 0,
    @SerializedName("imageUrl") val image_url: String = "",
    @SerializedName("tags") val tags: String = "",
    @SerializedName("title") val title: String = ""
)