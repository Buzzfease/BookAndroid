package com.buzz.bookandroid.di

import android.content.Context
import com.buzz.bookandroid.di.module.HttpModule
import com.buzz.bookandroid.di.module.ViewModelBuilderModule
import com.buzz.bookandroid.screen.booklist.di.BookListComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        HttpModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun bookListComponent(): BookListComponent.Factory

}
