package com.buzz.bookandroid.screen.booklist.di

import com.buzz.bookandroid.screen.booklist.view.BookListFragment
import dagger.Subcomponent

@Subcomponent(modules = [BookListModule::class])
internal interface BookListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BookListComponent
    }
    fun inject(fragment: BookListFragment)

}
