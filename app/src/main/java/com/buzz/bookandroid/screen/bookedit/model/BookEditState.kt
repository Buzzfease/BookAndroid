package com.buzz.bookandroid.screen.bookedit.model

import com.buzz.bookandroid.common.arch.model.ViewState
import com.buzz.bookandroid.widget.bookedit.BookEditItem

internal sealed class BookEditState : ViewState {

    data class Data(
        val dataSource: List<BookEditItem>
    ) : BookEditState()

    object Loading : BookEditState()
}