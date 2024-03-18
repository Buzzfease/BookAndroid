package com.buzz.bookandroid.screen.booklist.di

import androidx.lifecycle.ViewModel
import com.buzz.bookandroid.di.module.ViewModelKey
import com.buzz.bookandroid.network.repository.BookAndroidRepository
import com.buzz.bookandroid.screen.booklist.model.BookListModel
import com.buzz.bookandroid.screen.booklist.model.BookListReducer
import com.buzz.bookandroid.screen.booklist.model.BookListState
import com.buzz.bookandroid.screen.booklist.model.BookListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal interface BookListModule {
    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel::class)
    fun bindViewModel(viewModel: BookListViewModel): ViewModel

    companion object {
        @Provides
        fun provideState(): BookListState = BookListState.Loading

        @Provides
        fun providesBookListModel(
            repository: BookAndroidRepository,
            dispatcher: CoroutineDispatcher,
            reducer: BookListReducer
        ): BookListModel = BookListModel(
            repository,
            dispatcher,
            reducer
        )
    }

}