package com.buzz.bookandroid.screen.booklist.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class BookListModel(
    private val repository: NetworkRepository,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: BookListReducer,
) {
    suspend fun fetchBookList(): BookListState = withContext(dispatcher) {
        val bookListContent = repository.fetchBookList()
        handleResponseData(bookListContent)
    }

    suspend fun routeToDetail(id: String): BookListEvent = withContext(dispatcher) {
        reducer.reduceRouteToDetailEvent(id)
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
