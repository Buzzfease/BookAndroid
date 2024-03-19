package com.buzz.bookandroid.screen.bookedit.model

import androidx.lifecycle.viewModelScope
import com.buzz.bookandroid.common.arch.viewmodel.BaseViewModel
import com.buzz.bookandroid.network.model.Book
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class BookEditViewModel @Inject constructor(
    private val initialState: BookEditState,
    private val model: BookEditModel,
) : BaseViewModel<BookEditAction, BookEditState, BookEditEvent>() {

    override fun onAction(action: BookEditAction, currentState: BookEditState?) {
        when (action) {
            is BookEditAction.LoadData -> showBook(action.book)
            is BookEditAction.OnInsertButtonClick -> insertBook(action.book)
            is BookEditAction.OnUpdateButtonClick -> updateBook(action.book)
        }
    }

    private fun showBook(book: Book) {
        viewModelScope.launch {
            emitState(initialState)
            model.showBook(book).run {
                emitState(this)
            }
        }
    }

    private fun insertBook(book: Book) {
        viewModelScope.launch {
            emitState(initialState)
            model.insertBook(book).run {
                emitEvent(this)
            }
        }
    }

    private fun updateBook(book: Book) {
        viewModelScope.launch {
            emitState(initialState)
            model.updateBook(book).run {
                emitEvent(this)
            }
        }
    }
}