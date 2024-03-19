package com.buzz.bookandroid.adapterdelegate.adapter

import android.view.ViewGroup
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder

internal class DelegateAdapter<ITEM : BaseListItem>(
    private val delegateManager: DelegateManager<ITEM>
) : BaseListItemsAdapter<ITEM, BaseViewHolder<ITEM>>() {

    override fun getItemViewType(position: Int): Int =
        delegateManager.getItemViewType(items[position])

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> =
        delegateManager.onCreateViewHolder(parent, viewType) as BaseViewHolder<ITEM>

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.onBind(items[position])
    }

    override fun onPayload(holder: BaseViewHolder<ITEM>, position: Int, payloads: List<Any>) {
        holder.onPayload(items[position], payloads)
    }
}
