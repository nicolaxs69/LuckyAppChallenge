package com.lucky.luckyappchallenge.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucky.luckyappchallenge.utils.actions.OfferHomeActions
import com.lucky.luckyappchallenge.data.models.Offer
import com.lucky.luckyappchallenge.api.OffersServiceBuilder
import com.lucky.luckyappchallenge.utils.applySchedulers
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