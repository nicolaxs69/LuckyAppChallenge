package com.lucky.luckyappchallenge.models

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("title") val title: String = "",
    @SerializedName("sections") val sections: List<Section> = emptyList()
)
