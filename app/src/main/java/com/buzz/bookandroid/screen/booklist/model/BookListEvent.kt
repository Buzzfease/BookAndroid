package com.buzz.bookandroid.screen.booklist.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent
import java.util.Objects

internal sealed class BookListEvent : ViewEvent {

    @Keep
    data class GoToSearchPageEvent(
        val data: Objects,
    ) : BookListEvent()

    @Keep
    data class GoToDetailEvent(
        val data: String
    ) : BookListEvent()

}
