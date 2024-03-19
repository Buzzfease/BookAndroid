package com.buzz.bookandroid.screen.booklist.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent
import com.buzz.bookandroid.network.model.Book

internal sealed class BookListEvent : ViewEvent {

    object BookUpdated : BookListEvent()

    object BookInserted : BookListEvent()

    @Keep
    data class GoToDetailEvent(
        val data: String
    ) : BookListEvent()

    @Keep
    data class GoToInsertBookEvent(
        val book: Book,
    ) : BookListEvent()

}
