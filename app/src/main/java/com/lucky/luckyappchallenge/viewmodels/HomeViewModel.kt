package com.lucky.luckyappchallenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucky.luckyappchallenge.actions.OfferActions
import com.lucky.luckyappchallenge.models.Item
import com.lucky.luckyappchallenge.models.Offer
import com.lucky.luckyappchallenge.models.Section
import com.lucky.luckyappchallenge.services.OffersServiceBuilder
import com.lucky.luckyappchallenge.utils.applySchedulers
import com.lucky.luckyappchallenge.views.OfferItem
import com.lucky.luckyappchallenge.views.SectionItem
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _offerAction by lazy { MutableLiveData<OfferActions>() }
    val offerAction: LiveData<OfferActions> get() = _offerAction

    private val disposable = CompositeDisposable()

    fun getOffers() {
        disposable.add(
            OffersServiceBuilder.buildAvailableOrdersService().getOffers()
                .applySchedulers()
                .doOnSubscribe { showLoader(true) }
                .doFinally { showLoader(false) }
                .subscribe({ handleOffers(it) }, this::handleError)
        )
    }

    private fun handleOffers(offerObject: Offer) {
        _offerAction.value = OfferActions.DrawOffers(offerObject)
    }

    private fun showLoader(show: Boolean) {
        _offerAction.value = OfferActions.ShowLoader(show)
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    fun getOfferSection(data: List<Section>): List<SectionItem>? {
        return data.map { section ->
            SectionItem(section)
        }
    }
}