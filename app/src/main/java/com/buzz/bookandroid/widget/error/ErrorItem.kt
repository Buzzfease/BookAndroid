package com.buzz.bookandroid.widget.error

import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.widget.shimmerloading.ShimmerLoadingAdapter

internal object ErrorItem : BaseListItem {

    override val id: String
        get() = ShimmerLoadingAdapter.VIEW_TYPE.toString()

    override val viewType: Int = ErrorItemAdapter.VIEW_TYPE

    override fun calculatePayload(listItem: BaseListItem): Any? = null
}
