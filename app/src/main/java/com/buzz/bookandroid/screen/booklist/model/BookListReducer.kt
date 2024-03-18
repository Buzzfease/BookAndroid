package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.network.model.Book


/**
 * Reducer class for data mapping and create viewState,
 * also can add configuration injection, tagging...here
 */
internal class BookListReducer {

    fun reduceBookList(
        data: List<Book>
    ): BookListState {
        return BookListState.Data(dataSource = listOf())
    }


    fun reduceEmptyList(): BookListState {
        return BookListState.Empty
    }


    fun reduceBookListNetworkError(): BookListState {
        return BookListState.SummaryNetworkError
    }

}
