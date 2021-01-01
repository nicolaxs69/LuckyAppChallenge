package com.lucky.luckyappchallenge.views

import android.view.View
import com.bumptech.glide.Glide
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.databinding.OfferItemBinding
import com.lucky.luckyappchallenge.models.Item
import com.lucky.luckyappchallenge.utils.likesParser
import com.xwray.groupie.viewbinding.BindableItem
import java.text.DecimalFormat

class OfferItem(
    private val data: Item,
    private val offerListener: OfferItemListener
) : BindableItem<OfferItemBinding>() {

    private lateinit var binding: OfferItemBinding

    override fun getLayout(): Int = R.layout.offer_item

    override fun initializeViewBinding(view: View): OfferItemBinding {
        return OfferItemBinding.bind(view)
    }

    override fun bind(viewBinding: OfferItemBinding, position: Int) {
        binding = viewBinding
        setupView()
        initListener()
    }

    private fun initListener() {
        binding.containerOfferItem.setOnClickListener {
            offerListener.onClickOffer(data)
        }
    }

    private fun setupView() {
        data.let { offer ->
            binding.apply {
                productTitleTextView.text = offer.title
                productBrandTextView.text = offer.brand
                productTagTextView.text = offer.tags
                likesCounterTextView.text = offer.favorite_count.likesParser()
                setUrlImage(offer.image_url)
            }
        }
    }

    private fun OfferItemBinding.setUrlImage(url: String) {
        Glide.with(productImageView)
            .load(url)
            .centerCrop()
            .error(R.drawable.common_google_signin_btn_icon_dark)
            .fallback(R.drawable.common_full_open_on_phone)
            .into(productImageView)
    }

    interface OfferItemListener {
        fun onClickOffer(data: Item)
    }
}