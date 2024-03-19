package com.buzz.bookandroid.network.repository

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.wrapper.Content

interface NetworkRepository {

    suspend fun fetchBookList() : Content<List<Book>>

    suspend fun findBookById(id: String) : Content<Book>

    suspend fun deleteById(id: String) : Content<Unit>

    suspend fun insertBook(book: Book) : Content<Unit>

    suspend fun updateBook(book: Book) : Content<Unit>
}