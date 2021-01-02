package com.lucky.luckyappchallenge.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.actions.OfferDetailActions
import com.lucky.luckyappchallenge.databinding.FragmentOfferDetailBinding
import com.lucky.luckyappchallenge.models.OfferDetail
import com.lucky.luckyappchallenge.utils.likesParser
import com.lucky.luckyappchallenge.viewmodels.OfferDetailViewModel

class OfferDetailFragment : Fragment() {

    private var _binding: FragmentOfferDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var offerDetailViewModel: OfferDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        offerDetailViewModel = ViewModelProvider(this).get(OfferDetailViewModel::class.java)
        launchModeObserver()
        initListeners()
        loadData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun launchModeObserver() {
        offerDetailViewModel.apply {
            offerDetailAction.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is OfferDetailActions.ShowLoader -> TODO()
                    is OfferDetailActions.DrawOfferDetail -> updateView(it.offerDetail)
                }
            })
        }
    }

    private fun initListeners() {
        binding.apply {
            backArrowContainer.setOnClickListener {
                activity?.onBackPressed()
            }
            shareToolbarImageView.setOnClickListener {
                Toast.makeText(activity, "Shared button clicked!", Toast.LENGTH_SHORT).show()
            }
            likeToolbarImageView.setOnClickListener {
                Toast.makeText(activity, "We are glad you liked it!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadData() {
        val safeArgs: OfferDetailFragmentArgs by navArgs()
        offerDetailViewModel.getOfferDetail(safeArgs.offerId)
    }

    private fun updateView(offerDetail: OfferDetail) {
        binding.apply {
            with(offerDetail) {
                setUrlImage(imageUrl)
                brandTextView.text = brand
                titleProductdDetailTextView.text = title
                productDetailDescriptionTextView.text = description
                priceDiscountTextView.text = price.old
                priceTextView.text = price.new
                productDateTextView.text = expiration
                productRedemptionsTextView.text = redemptionsCap
                likesDetailCounterTextView.text = favoriteCount.likesParser()
            }
        }
    }

    private fun FragmentOfferDetailBinding.setUrlImage(url: String) {
        Glide.with(productDetailImageView)
            .load(url)
            .centerCrop()
            .error(R.drawable.common_google_signin_btn_icon_dark)
            .fallback(R.drawable.common_full_open_on_phone)
            .into(productDetailImageView)
    }
}