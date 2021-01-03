package com.lucky.luckyappchallenge.utils.actions

import com.lucky.luckyappchallenge.data.models.OfferDetail

sealed class OfferDetailActions {
    data class ShowLoader(val show: Boolean) : OfferDetailActions()
    data class DrawOfferDetail(val offerDetail: OfferDetail) : OfferDetailActions()
}