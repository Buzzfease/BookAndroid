package com.buzz.bookandroid

import android.app.Application
import com.buzz.bookandroid.di.AppComponentHolder

class BookAndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponentHolder.initComponent(applicationContext)
    }
}
