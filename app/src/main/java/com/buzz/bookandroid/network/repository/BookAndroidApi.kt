package com.buzz.bookandroid.network.repository

import com.buzz.bookandroid.network.model.Book
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookAndroidApi {

    @GET("/books/all")
    suspend fun fetchBookList(): List<Book>

    @GET("/books/{id}")
    suspend fun findBookById(@Path("id", encoded = true) id: Int): Book

    @GET("/books/delete/{id}")
    suspend fun deleteById(@Path("id", encoded = true) id: Int)

    @POST("/books/insert")
    suspend fun insertBook(@Body book: Book)

    @POST("/books/update")
    suspend fun updateBook(@Body book: Book)
}
