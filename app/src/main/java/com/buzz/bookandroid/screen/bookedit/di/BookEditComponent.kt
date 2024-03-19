package com.buzz.bookandroid.screen.bookedit.di

import com.buzz.bookandroid.screen.bookedit.view.BookEditFragment
import dagger.Subcomponent

@Subcomponent(modules = [BookEditModule::class])
internal interface BookEditComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BookEditComponent
    }
    fun inject(fragment: BookEditFragment)

}