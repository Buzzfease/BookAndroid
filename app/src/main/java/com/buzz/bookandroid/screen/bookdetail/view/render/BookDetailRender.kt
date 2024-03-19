package com.buzz.bookandroid.screen.bookdetail.view.render

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzz.bookandroid.adapterdelegate.adapter.DelegateAdapter
import com.buzz.bookandroid.adapterdelegate.adapter.DelegateManager
import com.buzz.bookandroid.adapterdelegate.model.BaseListItem
import com.buzz.bookandroid.common.arch.view.BaseRenderer
import com.buzz.bookandroid.widget.bookdetail.BookDetailItemAdapter
import com.buzz.bookandroid.widget.error.ErrorItem
import com.buzz.bookandroid.widget.error.ErrorItemAdapter
import com.buzz.bookandroid.widget.shimmerloading.ShimmerLoadingAdapter
import com.buzz.bookandroid.widget.shimmerloading.ShimmerLoadingItem
import javax.inject.Inject

class BookDetailRender @Inject constructor() : BaseRenderer {

    private lateinit var onRetryClick: () -> Unit

    private val delegateAdapter by lazy {
        DelegateAdapter(
            DelegateManager<BaseListItem>()
                .add(ShimmerLoadingAdapter())
                .add(BookDetailItemAdapter())
                .add(ErrorItemAdapter(onRetryClick = onRetryClick))
        )
    }

    fun init(
        recyclerView: RecyclerView,
        onRetryClick: () -> Unit
    ) {
        this.onRetryClick = onRetryClick
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

    fun renderError() {
        delegateAdapter.items = listOf(ErrorItem)
    }


    override fun clear() = Unit

}