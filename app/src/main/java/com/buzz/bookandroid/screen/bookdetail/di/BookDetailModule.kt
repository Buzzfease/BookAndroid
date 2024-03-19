package com.buzz.bookandroid.screen.bookdetail.di

import androidx.lifecycle.ViewModel
import com.buzz.bookandroid.di.module.ViewModelKey
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailModel
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailReducer
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailState
import com.buzz.bookandroid.screen.bookdetail.model.BookDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal interface BookDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    fun bindViewModel(viewModel: BookDetailViewModel): ViewModel

    companion object {
        @Provides
        fun provideState(): BookDetailState = BookDetailState.Loading

        @Provides
        fun providesBookDetailModel(
            repository: NetworkRepository,
            dispatcher: CoroutineDispatcher,
            reducer: BookDetailReducer
        ): BookDetailModel = BookDetailModel(
            repository,
            dispatcher,
            reducer
        )
    }

}