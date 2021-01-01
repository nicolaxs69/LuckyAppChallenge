package com.lucky.luckyappchallenge.actions

import com.lucky.luckyappchallenge.models.Offer

sealed class OfferHomeActions {
    data class ShowLoader(val show: Boolean) : OfferHomeActions()
    data class DrawOffers(val offer: Offer) : OfferHomeActions()
}