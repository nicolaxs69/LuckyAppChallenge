package com.lucky.luckyappchallenge.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lucky.luckyappchallenge.utils.actions.OfferDetailActions
import com.lucky.luckyappchallenge.data.models.OfferDetail
import com.lucky.luckyappchallenge.api.OffersServiceBuilder
import com.lucky.luckyappchallenge.utils.applySchedulers
import io.reactivex.disposables.CompositeDisposable

class OfferDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _offerDetailAction by lazy { MutableLiveData<OfferDetailActions>() }
    val offerDetailAction: LiveData<OfferDetailActions> get() = _offerDetailAction

    private val disposable = CompositeDisposable()

    fun getOfferDetail(offerId: String) {
        disposable.add(
            OffersServiceBuilder.buildOffersDetailService().getOffersDetail(offerId)
                .applySchedulers()
                .subscribe({ handleOffersDetail(it) }, this::handleError)
        )
    }

    private fun handleOffersDetail(offerDetail: OfferDetail) {
        _offerDetailAction.value = OfferDetailActions.DrawOfferDetail(offerDetail)
    }

    private fun showLoader(show: Boolean) {
        _offerDetailAction.postValue(OfferDetailActions.ShowLoader(show))
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }
}