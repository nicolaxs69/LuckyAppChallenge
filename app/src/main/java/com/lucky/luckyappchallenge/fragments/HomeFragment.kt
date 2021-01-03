package com.lucky.luckyappchallenge.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.actions.OfferHomeActions
import com.lucky.luckyappchallenge.databinding.FragmentHomeBinding
import com.lucky.luckyappchallenge.models.Item
import com.lucky.luckyappchallenge.models.Offer
import com.lucky.luckyappchallenge.models.Section
import com.lucky.luckyappchallenge.utils.hide
import com.lucky.luckyappchallenge.utils.show
import com.lucky.luckyappchallenge.viewmodels.HomeViewModel
import com.lucky.luckyappchallenge.views.SectionItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

internal class HomeFragment : Fragment(), SectionItem.SectionListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private var offerItemsSize: Int = 0

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        launchModeObserver()
        initListeners()
        setupGroupie()
        loadData(false)
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            loadData(true)
        }
    }

    private fun launchModeObserver() {
        homeViewModel.apply {
            offerAction.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is OfferHomeActions.ShowLoader -> showShimmer(it.show)
                    is OfferHomeActions.DrawOffers -> updateView(it.offer)
                }
            })
        }
    }

    private fun setupGroupie() {
        binding.sectionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sectionAdapter
        }
    }

    private fun loadData(isSwipe: Boolean) {
        homeViewModel.getOffers()
        if (isSwipe) {
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun updateView(offer: Offer) {
        binding.apply {
            sectionAdapter.clear()
            offerItemsSize = 0
            offer.sections.forEach { section ->
                offerItemsSize += section.items.size
            }
            offersCounterTextView.text =
                binding.root.resources.getString(R.string.luckyApp_offers_counter, offerItemsSize)

            getOfferSection(offer.sections)?.let {
                sectionAdapter.addAll(it)
            }
        }
    }

    private fun getOfferSection(data: List<Section>): List<SectionItem>? {
        return data.map { section ->
            SectionItem(section, this)
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

    override fun onClickOfferItem(data: Item) {
        val productId = data.detail_url.takeLast(1)
        val action = HomeFragmentDirections.actionHomeFragmentToOffersDetailFragment(productId)
        findNavController().navigate(action)
    }
}