package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.common.arch.model.ViewState
import com.buzz.bookandroid.widget.BookListItem

internal sealed class BookListState : ViewState {

    data class Data(
        val dataSource: List<BookListItem>
    ) : BookListState()

    object Loading : BookListState()

    object Empty : BookListState()

    object SummaryCommonError: BookListState()

    object SummaryNetworkError: BookListState()

}
