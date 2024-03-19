package com.buzz.bookandroid.screen.bookdetail.model

import androidx.lifecycle.viewModelScope
import com.buzz.bookandroid.common.arch.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class BookDetailViewModel @Inject constructor(
    private val initialState: BookDetailState,
    private val model: BookDetailModel,
) : BaseViewModel<BookDetailAction, BookDetailState, BookDetailEvent>() {

    override fun onAction(action: BookDetailAction, currentState: BookDetailState?) {
        when (action) {
            is BookDetailAction.LoadData -> loadData(currentState, action.id)
            is BookDetailAction.OnBookUpdateMenuClick -> routToUpdateBookPage()
        }
    }

    private fun loadData(currentState: BookDetailState?, id: String) {
        if (currentState !is BookDetailState.Data) {
            viewModelScope.launch {
                emitState(initialState)
                model.fetchBookById(id).run {
                    emitState(this)
                }
            }
        }
    }

    private fun routToUpdateBookPage() {
        viewModelScope.launch {
            emitEvent(model.routeToUpdateBookPage())
        }
    }
}
