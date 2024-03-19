package com.buzz.bookandroid.screen.bookedit.view.render

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzz.bookandroid.adapterdelegate.adapter.DelegateAdapter
import com.buzz.bookandroid.adapterdelegate.adapter.DelegateManager
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.common.arch.view.BaseRenderer
import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.widget.bookedit.BookEditItemAdapter
import com.buzz.bookandroid.widget.shimmerloading.ShimmerLoadingAdapter
import com.buzz.bookandroid.widget.shimmerloading.ShimmerLoadingItem
import javax.inject.Inject

class BookEditRender @Inject constructor() : BaseRenderer {

    private lateinit var onEditStateChange: (isActive: Boolean, book: Book) -> Unit

    private val delegateAdapter by lazy {
        DelegateAdapter(
            DelegateManager<BaseListItem>()
                .add(ShimmerLoadingAdapter())
                .add(
                    BookEditItemAdapter(
                        onEditStateChange = onEditStateChange
                    )
                )
        )
    }

    fun init(
        recyclerView: RecyclerView,
        onEditStateChange: (isActive: Boolean, book: Book) -> Unit
    ) {
        this.onEditStateChange = onEditStateChange
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = delegateAdapter
        }
    }

    fun renderData(data: List<BaseListItem>) {
        delegateAdapter.items = data
    }

    fun renderLoading() {
        delegateAdapter.items = listOf(ShimmerLoadingItem)
    }

    override fun clear() = Unit

}