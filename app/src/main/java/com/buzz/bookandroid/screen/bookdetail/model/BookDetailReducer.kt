package com.buzz.bookandroid.screen.bookdetail.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.screen.booklist.model.BookListState
import com.buzz.bookandroid.widget.bookdetail.BookDetailItem
import com.buzz.bookandroid.widget.booklist.BookListItem
import javax.inject.Inject

internal class BookDetailReducer @Inject constructor() {

    fun reduceBookDetail(
        book: Book
    ): BookDetailState = BookDetailState.Data(dataSource = createBookDetailDataSource(book))


    fun reduceBookDetailError(): BookDetailState = BookDetailState.Error


    private fun createBookDetailDataSource(book: Book) : List<BookDetailItem> {
        return listOf(
            BookDetailItem(
                id = book.id,
                name = book.name ?: "",
                author = book.author ?: "",
                publishYear = book.publishYear ?: "",
                isbn = book.isbn ?: ""
            )
        )
    }
}
