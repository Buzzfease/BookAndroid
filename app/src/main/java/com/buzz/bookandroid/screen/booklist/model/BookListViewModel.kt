package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.common.arch.viewmodel.BaseViewModel
import javax.inject.Inject

internal class BookListViewModel @Inject constructor(
    private val initialState: BookListState,
    private val model: BookListModel,
) : BaseViewModel<BookListAction, BookListState, BookListEvent>() {

    override fun onAction(action: BookListAction, currentState: BookListState?) {

    }
}
