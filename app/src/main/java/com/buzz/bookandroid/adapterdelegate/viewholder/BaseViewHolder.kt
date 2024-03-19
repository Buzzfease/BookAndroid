package com.buzz.bookandroid.adapterdelegate.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem

internal abstract class BaseViewHolder<ITEM: BaseListItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: ITEM)

    abstract fun onPayload(item: ITEM, payloads: List<Any>)
}
