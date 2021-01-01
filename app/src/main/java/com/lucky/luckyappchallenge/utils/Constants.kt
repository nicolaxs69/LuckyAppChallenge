package com.lucky.luckyappchallenge.utils

import java.text.DecimalFormat

const val BASE_URL = "https://www.nasable.com/luckytest/"

fun Number.likesParser(): String? {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue: Long = this.toLong()
    val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
        DecimalFormat("#0.0").format(
            numValue / Math.pow(
                10.0,
                base * 3.toDouble()
            )
        ) + suffix[base]
    } else {
        DecimalFormat("#,##0").format(numValue)
    }
}