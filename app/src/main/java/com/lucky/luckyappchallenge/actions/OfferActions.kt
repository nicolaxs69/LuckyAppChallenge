package com.lucky.luckyappchallenge.actions

import com.lucky.luckyappchallenge.models.Offer

sealed class OfferActions {
    data class ShowLoader(val show: Boolean) : OfferActions()
    data class DrawOffers(val offer: Offer) : OfferActions()
}