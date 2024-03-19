package com.buzz.bookandroid.widget.shimmerloading

import android.view.LayoutInflater
import android.view.ViewGroup
import com.buzz.bookandroid.R
import com.buzz.bookandroid.adapterdelegate.adapter.AdapterDelegate
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.databinding.ItemLoadingBinding
import com.facebook.shimmer.ShimmerFrameLayout

internal class ShimmerLoadingAdapter : AdapterDelegate<ShimmerLoadingItem> {

    override val viewType: Int
        get() = VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out ShimmerLoadingItem> {
        val binding = ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShimmerLoadingViewHolder(
            view = binding.loadingView
        )
    }

    companion object {
        val VIEW_TYPE = R.layout.item_loading
    }
}

internal class ShimmerLoadingViewHolder(
    private val view: ShimmerFrameLayout
) : BaseViewHolder<ShimmerLoadingItem>(view) {

    override fun onBind(item: ShimmerLoadingItem) {
        view.startShimmer()
    }

    override fun onPayload(item: ShimmerLoadingItem, payloads: List<Any>) = Unit
}
