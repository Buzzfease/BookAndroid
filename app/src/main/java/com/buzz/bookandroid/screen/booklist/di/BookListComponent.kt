package com.buzz.bookandroid.screen.booklist.di

import dagger.Subcomponent

@Subcomponent(modules = [BookListModule::class])
internal interface BookListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BookListComponent
    }

}
