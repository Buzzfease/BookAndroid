package com.buzz.bookandroid.screen.bookdetail.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent
import java.util.Objects

internal sealed class BookDetailEvent : ViewEvent {

    @Keep
    data class GoToUpdateBookEvent(
        val data: Objects,
    ) : BookDetailEvent()

    @Keep
    data class GoToDeleteBookEvent(
        val data: Objects
    ) : BookDetailEvent()

}