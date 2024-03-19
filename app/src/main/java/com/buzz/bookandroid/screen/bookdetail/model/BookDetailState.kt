package com.buzz.bookandroid.screen.bookdetail.model

import com.buzz.bookandroid.common.arch.model.ViewState
import com.buzz.bookandroid.widget.bookdetail.BookDetailItem

internal sealed class BookDetailState : ViewState {

    data class Data(
        val dataSource: List<BookDetailItem>
    ) : BookDetailState()

    object Loading : BookDetailState()

    object Error : BookDetailState()
}