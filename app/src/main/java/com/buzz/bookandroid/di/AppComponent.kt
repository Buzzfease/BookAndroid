package com.buzz.bookandroid.di

import android.content.Context
import com.buzz.bookandroid.di.module.HttpModule
import com.buzz.bookandroid.di.module.ViewModelBuilderModule
import com.buzz.bookandroid.screen.MainComponent
import com.buzz.bookandroid.screen.bookdetail.di.BookDetailComponent
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
internal interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun provideContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

    fun bookListComponent(): BookListComponent.Factory

    fun bookDetailComponent(): BookDetailComponent.Factory

}
