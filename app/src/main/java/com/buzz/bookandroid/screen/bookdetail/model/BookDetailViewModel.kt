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
        }
    }

    private fun loadData(currentState: BookDetailState?, id: Int) {
        if (currentState !is BookDetailState.Data) {
            viewModelScope.launch {
                emitState(initialState)
                model.fetchBookById(id).run {
                    emitState(this)
                }
            }
        }
    }
}
