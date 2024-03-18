package com.buzz.bookandroid

import android.app.Application
import com.buzz.bookandroid.di.AppComponent
import com.buzz.bookandroid.di.DaggerAppComponent

class BookAndroidApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}
