package com.lucky.luckyappchallenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucky.luckyappchallenge.actions.OfferHomeActions
import com.lucky.luckyappchallenge.models.Offer
import com.lucky.luckyappchallenge.models.Section
import com.lucky.luckyappchallenge.services.OffersServiceBuilder
import com.lucky.luckyappchallenge.utils.applySchedulers
import com.lucky.luckyappchallenge.views.SectionItem
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _offerAction by lazy { MutableLiveData<OfferHomeActions>() }
    val offerAction: LiveData<OfferHomeActions> get() = _offerAction

    private val disposable = CompositeDisposable()

    fun getOffers() {
        disposable.add(
            OffersServiceBuilder.buildOffersAvailableService().getOffers()
                .applySchedulers()
                .doOnSubscribe { showLoader(true) }
                .doFinally { showLoader(false) }
                .subscribe({ handleOffers(it) }, this::handleError)
        )
    }

    private fun handleOffers(offerObject: Offer) {
        _offerAction.value = OfferHomeActions.DrawOffers(offerObject)
    }

    private fun showLoader(show: Boolean) {
        _offerAction.postValue(OfferHomeActions.ShowLoader(show))
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }
}