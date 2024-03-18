package com.buzz.bookandroid.network.repository

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.wrapper.asContent

/**
 * repository class used for handle multiple retrofit services here(if you have)
 * also use `asContent` for base error handle
 */
class BookAndroidRepository(
    private val bookAndroidApi: BookAndroidApi
) {

    suspend fun fetchBookList() = asContent {
        bookAndroidApi.fetchBookList()
    }

    suspend fun findBookById(id: Int) = asContent {
        bookAndroidApi.findBookById(id)
    }

    suspend fun deleteById(id: Int) = asContent {
        bookAndroidApi.deleteById(id)
    }

    suspend fun insertBook(book: Book) = asContent {
        bookAndroidApi.insertBook(book)
    }

    suspend fun updateBook(book: Book) = asContent {
        bookAndroidApi.updateBook(book)
    }
}
