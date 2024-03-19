package com.buzz.bookandroid.adapterdelegate.adapter

import android.view.ViewGroup
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder

internal class DelegateManager<ITEM : BaseListItem> {

    private val delegates = mutableListOf<AdapterDelegate<out ITEM>>()

    fun add(adapter: AdapterDelegate<out ITEM>): DelegateManager<ITEM> =
        apply { delegates.add(adapter) }

    fun getItemViewType(itemView: ITEM): Int =
        delegates
            .firstOrNull { it.viewType == itemView.viewType }
            ?.viewType
            ?: throw IllegalArgumentException("No delegate for $itemView")

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out ITEM> =
        delegates.first { it.viewType == viewType }.onCreateViewHolder(parent)
}
