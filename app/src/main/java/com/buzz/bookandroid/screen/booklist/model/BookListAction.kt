package com.buzz.bookandroid.screen.booklist.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.intent.Action

sealed class BookListAction : Action {
    object LoadData : BookListAction()

    @Keep
    data class OnBookItemClick(val id: String) : BookListAction()
}
