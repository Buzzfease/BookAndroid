package com.buzz.bookandroid.screen.booklist.model

internal interface BookListModel  {
    suspend fun fetchBookList(): BookListState
    suspend fun routeToDetail(id: String): BookListEvent
    suspend fun routeToSearch(): BookListEvent
}
