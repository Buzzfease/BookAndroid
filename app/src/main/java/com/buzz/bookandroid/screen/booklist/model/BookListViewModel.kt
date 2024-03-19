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
            is BookListAction.OnBookItemClick -> routeToDetail(action.id)
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
}
