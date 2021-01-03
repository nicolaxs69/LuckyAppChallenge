package com.lucky.luckyappchallenge.utils.actions

import com.lucky.luckyappchallenge.data.models.Offer

sealed class OfferHomeActions {
    data class ShowLoader(val show: Boolean) : OfferHomeActions()
    data class DrawOffers(val offer: Offer) : OfferHomeActions()
}