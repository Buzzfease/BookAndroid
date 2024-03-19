package com.buzz.bookandroid.adapterdelegate.adapter

import android.view.ViewGroup
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder

internal interface AdapterDelegate<ITEM : BaseListItem> {

    val viewType: Int

    fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out ITEM>
}
