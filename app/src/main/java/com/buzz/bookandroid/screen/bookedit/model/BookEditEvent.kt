package com.buzz.bookandroid.screen.bookedit.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.model.ViewEvent

internal sealed class BookEditEvent : ViewEvent {

    @Keep
    object GoToBookListEvent: BookEditEvent()

    @Keep
    data class ShowErrorBannerEvent(val errorMessage: String): BookEditEvent()

}