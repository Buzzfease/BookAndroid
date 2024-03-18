package com.buzz.bookandroid.di

import android.content.Context
import com.buzz.bookandroid.di.module.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance applicationContext: Context): AppComponent

    }

}