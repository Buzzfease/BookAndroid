package com.buzz.bookandroid

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import com.buzz.bookandroid.network.wrapper.asContent
import java.net.SocketTimeoutException

class MockRepository : NetworkRepository {

    var bookListError: Boolean = false

    override suspend fun fetchBookList(): Content<List<Book>> = asContent {
        throwIfNeeded(bookListError)
        listOf(
            Book(
                id = "1",
                name = "aaa",
                author = "bbb",
                publishYear = "ccc",
                isbn = "ddd"
            ),
            Book(
                id = "2",
                name = "aaaAAA",
                author = "bbbBBB",
                publishYear = "cccCCC",
                isbn = "dddDDD"
            )
        )
    }

    private fun throwIfNeeded(
        condition: Boolean
    ) {
        if (condition) {
            throw SocketTimeoutException()
        }
    }

    override suspend fun findBookById(id: Int): Content<Book> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteById(id: Int): Content<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBook(book: Book): Content<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBook(book: Book): Content<Unit> {
        TODO("Not yet implemented")
    }
}

internal fun MockRepository.withBookListError(): MockRepository {
    bookListError = true
    return this
}