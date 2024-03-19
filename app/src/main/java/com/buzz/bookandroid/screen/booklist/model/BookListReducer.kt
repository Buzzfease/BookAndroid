package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.widget.booklist.BookListItem
import javax.inject.Inject


/**
 * Reducer class for data mapping and create viewState,
 * also can add configuration injection, tagging...here
 */
internal class BookListReducer @Inject constructor() {

    fun reduceBookList(
        data: List<Book>
    ): BookListState = BookListState.Data(dataSource = createBookListDataSource(data))


    fun reduceBookListError(): BookListState = BookListState.Error

    fun reduceRouteToDetailEvent(id: String): BookListEvent = BookListEvent.GoToDetailEvent(id)

    private fun createBookListDataSource(data: List<Book>) : List<BookListItem> {
        return data
            .asSequence()
            .sortedBy { it.id }
            .map {
                BookListItem(
                    id = it.id,
                    name = it.name ?: "",
                    author = it.author ?: "",
                    publishYear = it.publishYear ?: "",
                    isbn = it.isbn ?: ""
                )
            }.toList()
    }
}
