package com.buzz.bookandroid.screen.bookedit.model

import com.buzz.bookandroid.network.model.Book
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.network.wrapper.Content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class BookEditModel(
    private val repository: NetworkRepository,
    private val dispatcher: CoroutineDispatcher,
    private val reducer: BookEditReducer,
) {

    fun showBook(book: Book): BookEditState = reducer.reduceBook(book)


    suspend fun updateBook(book: Book): BookEditEvent = withContext(dispatcher) {
        when (repository.updateBook(book)) {
            is Content.Data -> {
                reducer.reduceUpdateSuccessEvent()
            }
            is Content.Error -> {
                reducer.reduceErrorBanner("update failed")
            }
        }
    }

    suspend fun insertBook(book: Book): BookEditEvent = withContext(dispatcher) {
        when (repository.insertBook(book)) {
            is Content.Data -> {
                reducer.reduceInsertSuccessEvent()
            }
            is Content.Error -> {
                reducer.reduceErrorBanner("insert failed")
            }
        }
    }
}