package com.buzz.bookandroid.screen.bookedit.model

import androidx.annotation.Keep
import com.buzz.bookandroid.common.arch.intent.Action
import com.buzz.bookandroid.network.model.Book

sealed class BookEditAction : Action {

    @Keep
    data class LoadData(val book: Book) : BookEditAction()

    @Keep
    data class OnUpdateButtonClick(val book: Book) : BookEditAction()

    @Keep
    data class OnInsertButtonClick(val book: Book) : BookEditAction()

}
