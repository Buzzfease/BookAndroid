package com.buzz.bookandroid.widget.error

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.buzz.bookandroid.R
import com.buzz.bookandroid.adapterdelegate.adapter.AdapterDelegate
import com.buzz.bookandroid.adapterdelegate.viewholder.BaseViewHolder
import com.buzz.bookandroid.databinding.ItemErrorBinding

internal class ErrorItemAdapter(
    private val onRetryClick: () -> Unit
) : AdapterDelegate<ErrorItem> {

    override val viewType: Int
        get() = VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<out ErrorItem> {
        val binding = ItemErrorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ErrorItemViewHolder(
            rlError = binding.rlError,
            onRetryClick = onRetryClick
        )
    }

    companion object {
        val VIEW_TYPE = R.layout.item_error
    }
}

internal class ErrorItemViewHolder(
   private val rlError: RelativeLayout,
   private val onRetryClick:() -> Unit
) : BaseViewHolder<ErrorItem>(rlError) {

    override fun onBind(item: ErrorItem) {
        rlError.setOnClickListener {
            onRetryClick()
        }
    }

    override fun onPayload(item: ErrorItem, payloads: List<Any>) = Unit
}
