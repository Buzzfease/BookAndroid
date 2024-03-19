package com.buzz.bookandroid.screen.booklist.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent

internal sealed class BookListEvent : ViewEvent {

    object BookUpdated : BookListEvent()

    @Keep
    data class GoToDetailEvent(
        val data: String
    ) : BookListEvent()

}
