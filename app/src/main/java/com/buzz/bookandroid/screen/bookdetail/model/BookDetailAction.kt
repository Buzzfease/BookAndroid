package com.buzz.bookandroid.screen.bookdetail.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.intent.Action

sealed class BookDetailAction : Action {
    @Keep
    data class LoadData(val id: String) : BookDetailAction()
    @Keep
    object OnBookUpdateMenuClick : BookDetailAction()
    @Keep
    object OnBookDeleteMenuClick : BookDetailAction()

    @Keep
    data class DeleteBook(val id: String) : BookDetailAction()
}