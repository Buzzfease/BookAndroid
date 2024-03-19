package com.buzz.bookandroid.adapterdelegate.utils

import androidx.recyclerview.widget.DiffUtil
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem

internal class ListItemDiffUtilCallback<ITEM : BaseListItem> : DiffUtil.ItemCallback<ITEM>() {

    override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        oldItem.areItemsTheSame(newItem)

    override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        oldItem.equals(newItem)

    override fun getChangePayload(oldItem: ITEM, newItem: ITEM): Any? =
        oldItem.calculatePayload(newItem)
}
