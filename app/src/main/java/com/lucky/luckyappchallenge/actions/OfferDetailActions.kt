package com.lucky.luckyappchallenge.actions

import com.lucky.luckyappchallenge.models.OfferDetail

sealed class OfferDetailActions {
    data class ShowLoader(val show: Boolean) : OfferDetailActions()
    data class DrawOfferDetail(val offerDetail: OfferDetail) : OfferDetailActions()
}