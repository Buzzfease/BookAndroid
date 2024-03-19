package com.buzz.bookandroid.screen.bookdetail.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent
import com.buzz.bookandroid.network.model.Book

internal sealed class BookDetailEvent : ViewEvent {

    @Keep
    data class GoToUpdateBookEvent(
        val book: Book,
    ) : BookDetailEvent()

}