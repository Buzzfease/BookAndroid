package com.buzz.bookandroid.screen.bookedit.di

import androidx.lifecycle.ViewModel
import com.buzz.bookandroid.di.module.ViewModelKey
import com.buzz.bookandroid.network.repository.NetworkRepository
import com.buzz.bookandroid.screen.bookedit.model.BookEditModel
import com.buzz.bookandroid.screen.bookedit.model.BookEditReducer
import com.buzz.bookandroid.screen.bookedit.model.BookEditState
import com.buzz.bookandroid.screen.bookedit.model.BookEditViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal interface BookEditModule {
    @Binds
    @IntoMap
    @ViewModelKey(BookEditViewModel::class)
    fun bindViewModel(viewModel: BookEditViewModel): ViewModel

    companion object {
        @Provides
        fun provideState(): BookEditState = BookEditState.Loading

        @Provides
        fun providesBookEditModel(
            repository: NetworkRepository,
            dispatcher: CoroutineDispatcher,
            reducer: BookEditReducer
        ): BookEditModel = BookEditModel(
            repository,
            dispatcher,
            reducer
        )
    }

}