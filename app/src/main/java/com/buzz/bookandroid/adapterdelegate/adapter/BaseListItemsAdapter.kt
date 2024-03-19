package com.buzz.bookandroid.adapterdelegate.adapter

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.adapterdelegate.utils.ListItemDiffUtilCallback

internal abstract class BaseListItemsAdapter<ITEM : BaseListItem, HOLDER : BaseViewHolder<ITEM>> :
    RecyclerView.Adapter<HOLDER>() {

    private val differ by lazy {
        AsyncListDiffer<ITEM>(this, ListItemDiffUtilCallback())
    }

    var items: List<ITEM>
        get() = differ.currentList
        set(newList) {
            differ.submitList(newList)
        }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: HOLDER, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            onPayload(holder, position, payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    abstract fun onPayload(holder: HOLDER, position: Int, payloads: List<Any>)
}
