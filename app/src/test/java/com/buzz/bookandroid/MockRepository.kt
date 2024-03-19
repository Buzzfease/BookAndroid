package com.buzz.bookandroid

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import com.buzz.bookandroid.network.wrapper.asContent
import java.net.SocketTimeoutException

class MockRepository : NetworkRepository {

    var bookListError: Boolean = false
    var bookDetailError: Boolean = false

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



    override suspend fun findBookById(id: String): Content<Book> = asContent {
        throwIfNeeded(bookDetailError)
        Book(
            id = "1",
            name = "aaa",
            author = "bbb",
            publishYear = "ccc",
            isbn = "ddd"
        )
    }

    override suspend fun deleteById(id: String): Content<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBook(book: Book): Content<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBook(book: Book): Content<Unit> {
        TODO("Not yet implemented")
    }
}

private fun throwIfNeeded(
    condition: Boolean
) {
    if (condition) {
        throw SocketTimeoutException()
    }
}

internal fun MockRepository.withBookListError(): MockRepository {
    bookListError = true
    return this
}

internal fun MockRepository.withBookDetailError(): MockRepository {
    bookDetailError = true
    return this
}