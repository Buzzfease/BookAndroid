package com.buzz.bookandroid.screen.booklist.di

import androidx.lifecycle.ViewModel
import com.buzz.bookandroid.di.module.ViewModelKey
import com.buzz.bookandroid.screen.booklist.model.BookListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface BookListModule {
    @Binds
    @IntoMap
    @ViewModelKey(BookListViewModel::class)
    fun bindViewModel(viewModel: BookListViewModel): ViewModel

}