package com.buzz.bookandroid.screen.bookedit.view.render

import android.widget.TextView
import com.buzz.bookandroid.common.arch.view.BaseRenderer
import com.buzz.bookandroid.network.model.Book
import javax.inject.Inject

class BookEditSubmitButtonRender @Inject constructor() : BaseRenderer {

    private lateinit var submitButton: TextView
    private lateinit var onSubmitButtonClick: (book: Book) -> Unit
    private lateinit var book: Book

    fun init(
        submitButton: TextView,
        onSubmitButtonClick: (book: Book) -> Unit
    ) {
        this.submitButton = submitButton
        this.onSubmitButtonClick = onSubmitButtonClick
        submitButton.setOnClickListener {
            onSubmitButtonClick.invoke(book)
        }
    }

    fun renderSubmitButtonEnable() {
        submitButton.isEnabled = true
    }

    fun renderSubmitButtonDisable() {
        submitButton.isEnabled = false
    }

    fun setBook(book: Book) {
        this.book = book
    }


    override fun clear() = Unit

}