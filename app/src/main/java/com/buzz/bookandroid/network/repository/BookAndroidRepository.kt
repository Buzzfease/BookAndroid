package com.buzz.bookandroid.network.repository

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.wrapper.asContent

/**
 * repository class used for handle multiple retrofit services here(if you have)
 * also use `asContent` for base error handle
 */
class BookAndroidRepository(
    private val bookAndroidApi: BookAndroidApi
): NetworkRepository {

    override suspend fun fetchBookList() = asContent {
        bookAndroidApi.fetchBookList()
    }

    override suspend fun findBookById(id: Int) = asContent {
        bookAndroidApi.findBookById(id)
    }

    override suspend fun deleteById(id: Int) = asContent {
        bookAndroidApi.deleteById(id)
    }

    override suspend fun insertBook(book: Book) = asContent {
        bookAndroidApi.insertBook(book)
    }

    override suspend fun updateBook(book: Book) = asContent {
        bookAndroidApi.updateBook(book)
    }
}
