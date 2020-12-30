package com.lucky.luckyappchallenge.views

import android.view.View
import com.bumptech.glide.Glide
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.databinding.OfferItemBinding
import com.lucky.luckyappchallenge.models.Item
import com.xwray.groupie.viewbinding.BindableItem
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class OfferItem(
    private val data: Item
) : BindableItem<OfferItemBinding>() {

    private lateinit var binding: OfferItemBinding

    override fun getLayout(): Int = R.layout.offer_item

    override fun initializeViewBinding(view: View): OfferItemBinding {
        return OfferItemBinding.bind(view)
    }

    override fun bind(viewBinding: OfferItemBinding, position: Int) {
        binding = viewBinding
        setupView()
    }

    private fun setupView() {
        data.let { offer ->
            binding.apply {
                productTitleTextView.text = offer.title
                productBrandTextView.text = offer.brand
                productTagTextView.text = offer.tags
                likesCounterTextView.text = prettyCount(offer.favorite_count)
                setUrlImage(offer.image_url)
            }
        }
    }


    fun prettyCount(number: Number): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue: Long = number.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / Math.pow(
                    10.0,
                    base * 3.toDouble()
                )
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
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
}