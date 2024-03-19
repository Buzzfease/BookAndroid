package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.BookAndroidRepository
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class BookListModel(
    private val repository: NetworkRepository,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: BookListReducer,
) {
    suspend fun fetchPaymentListItems(): BookListState = withContext(dispatcher) {
        val paymentListContent = repository.fetchBookList()
        handleResponseData(paymentListContent)
    }

    private fun handleResponseData(bookListContent: Content<List<Book>>): BookListState {
        return when (bookListContent) {
            is Content.Data -> {
                reducer.reduceBookList(bookListContent.data)
            }
            is Content.Error -> {
                reducer.reduceBookListError()
            }
        }
    }
}
