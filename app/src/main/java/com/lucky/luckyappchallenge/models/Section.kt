package com.lucky.luckyappchallenge.models

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("title") val title: String = "",
    @SerializedName("items") val items: List<Item> = emptyList()
)