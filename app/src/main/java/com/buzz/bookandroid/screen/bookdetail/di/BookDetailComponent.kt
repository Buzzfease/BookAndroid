package com.buzz.bookandroid.screen.bookdetail.di

import com.buzz.bookandroid.screen.bookdetail.view.BookDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [BookDetailModule::class])
internal interface BookDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BookDetailComponent
    }
    fun inject(fragment: BookDetailFragment)

}