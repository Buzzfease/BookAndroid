package com.buzz.bookandroid.widget.shimmerloading

import com.buzz.bookandroid.adapterdelegate.model.BaseListItem

internal object ShimmerLoadingItem : BaseListItem {

    override val id: String
        get() = ShimmerLoadingAdapter.VIEW_TYPE.toString()

    override val viewType: Int = ShimmerLoadingAdapter.VIEW_TYPE

    override fun calculatePayload(listItem: BaseListItem): Any? = null
}
