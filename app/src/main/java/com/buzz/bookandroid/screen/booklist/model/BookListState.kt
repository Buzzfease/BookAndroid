package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.common.arch.model.ViewState
import com.buzz.bookandroid.widget.booklist.BookListItem

internal sealed class BookListState : ViewState {

    data class Data(
        val dataSource: List<BookListItem>
    ) : BookListState()

    object Loading : BookListState()

    object Error : BookListState()
}
