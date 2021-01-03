package com.lucky.luckyappchallenge.ui.views

import com.lucky.luckyappchallenge.databinding.OfferSectionBinding
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.data.models.Item
import com.lucky.luckyappchallenge.data.models.Section
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class SectionItem(
    private val data: Section,
    private val sectionListener: SectionListener
) : BindableItem<OfferSectionBinding>(), OfferItem.OfferItemListener {

    private lateinit var binding: OfferSectionBinding

    override fun getLayout(): Int = R.layout.offer_section

    private val itemsAdapter: GroupAdapter<GroupieViewHolder> by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun initializeViewBinding(view: View): OfferSectionBinding {
        return OfferSectionBinding.bind(view)
    }

    override fun bind(viewBinding: OfferSectionBinding, position: Int) {
        binding = viewBinding
        setupGroupie()
        setupView()
    }

    private fun setupView() {
        data.let { section ->
            binding.apply {
                sectionTitleTextView.text = section.title
            }
        }
        itemsAdapter.clear()
        getOfferItems(data.items)?.let {
            itemsAdapter.addAll(it)
        }
    }

    private fun setupGroupie() {
        binding.itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
    }

    private fun getOfferItems(data: List<Item>): List<OfferItem>? {
        return data.map { offer ->
            OfferItem(offer, this)
        }
    }

    interface SectionListener {
        fun onClickOfferItem(data: Item)
    }

    override fun onClickOffer(data: Item) {
        sectionListener.onClickOfferItem(data)
    }
}