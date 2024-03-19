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

    suspend fun fetchBookById(id: Int): BookDetailState = withContext(dispatcher) {
        val bookDetailContent = repository.findBookById(id)
        handleResponseData(bookDetailContent)
    }

    private fun handleResponseData(bookDetailContent: Content<Book>): BookDetailState {
        return when (bookDetailContent) {
            is Content.Data -> {
                reducer.reduceBookDetail(bookDetailContent.data)
            }
            is Content.Error -> {
                reducer.reduceBookDetailError()
            }
        }
    }
}
