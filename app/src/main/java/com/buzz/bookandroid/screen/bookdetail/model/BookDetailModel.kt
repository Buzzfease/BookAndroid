package com.buzz.bookandroid.screen.bookdetail.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


internal class BookDetailModel(
    private val repository: NetworkRepository,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: BookDetailReducer,
) {

    private lateinit var book: Book

    suspend fun fetchBookById(id: String): BookDetailState = withContext(dispatcher) {
        val bookDetailContent = repository.findBookById(id)
        handleResponseData(bookDetailContent)
    }

    private fun handleResponseData(bookDetailContent: Content<Book>): BookDetailState {
        return when (bookDetailContent) {
            is Content.Data -> {
                book = bookDetailContent.data
                reducer.reduceBookDetail(bookDetailContent.data)
            }
            is Content.Error -> {
                reducer.reduceBookDetailError()
            }
        }
    }

    suspend fun routeToUpdateBookPage(): BookDetailEvent = withContext(dispatcher) {
        reducer.reduceRouteToUpdateBookEvent(book)
    }
}
