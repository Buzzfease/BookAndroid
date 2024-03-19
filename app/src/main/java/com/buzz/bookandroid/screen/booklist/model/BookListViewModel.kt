package com.buzz.bookandroid.screen.booklist.model

import androidx.lifecycle.viewModelScope
import com.buzz.bookandroid.common.arch.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class BookListViewModel @Inject constructor(
    private val initialState: BookListState,
    private val model: BookListModel,
) : BaseViewModel<BookListAction, BookListState, BookListEvent>() {

    override fun onAction(action: BookListAction, currentState: BookListState?) {
        when (action) {
            BookListAction.LoadData -> loadData(currentState)
            BookListAction.OnBookUpdated -> onBookUpdated()
            BookListAction.OnBookInserted -> onBookInserted()
            BookListAction.OnBookDeleted -> onBookDeleted()
            is BookListAction.OnBookItemClick -> routeToDetail(action.id)
            is BookListAction.OnBookInsertMenuClick -> routToInsertBookPage()
        }
    }

    private fun loadData(currentState: BookListState?) {
        if (currentState !is BookListState.Data) {
            viewModelScope.launch {
                emitState(initialState)
                model.fetchBookList().run {
                    emitState(this)
                }
            }
        }
    }

    private fun routeToDetail(id: String) {
        viewModelScope.launch {
            emitEvent(model.routeToDetail(id))
        }
    }

    private fun onBookUpdated() {
        viewModelScope.launch {
            emitEvent(BookListEvent.BookUpdated)
            emitState(initialState)
            emitState(model.fetchBookList())
        }
    }

    private fun onBookInserted() {
        viewModelScope.launch {
            emitEvent(BookListEvent.BookInserted)
            emitState(initialState)
            emitState(model.fetchBookList())
        }
    }

    private fun onBookDeleted() {
        viewModelScope.launch {
            emitEvent(BookListEvent.BookDeleted)
            emitState(initialState)
            emitState(model.fetchBookList())
        }
    }

    private fun routToInsertBookPage() {
        viewModelScope.launch {
            emitEvent(model.routeToInsertBookPage())
        }
    }
}
