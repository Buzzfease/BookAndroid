package com.buzz.bookandroid.screen.bookedit.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.widget.bookedit.BookEditItem
import javax.inject.Inject

internal class BookEditReducer @Inject constructor() {

    fun reduceBook(
        data: Book
    ): BookEditState = BookEditState.Data(dataSource = createBookDataSource(data))

    fun reduceRouteToBookListEvent(): BookEditEvent = BookEditEvent.GoToBookListEvent

    fun reduceErrorBanner(errorMessage: String): BookEditEvent = BookEditEvent.ShowErrorBannerEvent(errorMessage)

    private fun createBookDataSource(book: Book) : List<BookEditItem> {
        return listOf(
            BookEditItem(
                id = book.id,
                name = book.name ?: "",
                author = book.author ?: "",
                publishYear = book.publishYear ?: "",
                isbn = book.isbn ?: ""
            )
        )
    }

}