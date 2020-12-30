package com.lucky.luckyappchallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.actions.OfferActions
import com.lucky.luckyappchallenge.databinding.FragmentHomeBinding
import com.lucky.luckyappchallenge.models.Offer
import com.lucky.luckyappchallenge.models.Section
import com.lucky.luckyappchallenge.utils.hide
import com.lucky.luckyappchallenge.utils.show
import com.lucky.luckyappchallenge.viewmodels.HomeViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private var offerItemsSize: Int = 0

    private val sectionAdapter: GroupAdapter<GroupieViewHolder> by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        launchModeObserver()
        setupGroupie()
        loadData()
    }

    private fun loadData() {
        homeViewModel.getOffers()
    }

    private fun launchModeObserver() {
        homeViewModel.apply {
            offerAction.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is OfferActions.ShowLoader -> showShimmer(it.show)
                    is OfferActions.DrawOffers -> updateView(it.offer)
                }
            })
        }
    }

    private fun updateView(offer: Offer) {
        binding.apply {
            sectionAdapter.clear()
            offer.sections.forEach { section ->
                offerItemsSize += section.items.size
            }
            offersCounterTextView.text =
                binding.root.resources.getString(R.string.luckyApp_offers_counter, offerItemsSize)
            homeViewModel.getOfferSection(offer.sections)?.let {
                sectionAdapter.addAll(it)
            }
        }
    }

    private fun setupGroupie() {
        binding.sectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sectionAdapter
        }
    }

    private fun showShimmer(show: Boolean) {
        binding.apply {
            sectionRecyclerShimmer.apply {
                if (show) {
                    sectionsRecyclerView.hide()
                    offersCounterTextView.hide()
                    visibility = View.VISIBLE
                    startShimmer()
                } else {
                    stopShimmer()
                    visibility = View.GONE
                    offersCounterTextView.show()
                    sectionsRecyclerView.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}