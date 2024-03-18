package com.buzz.bookandroid.di

import android.app.Application
import android.content.Context

internal object AppComponentHolder {

    private var component: AppComponent? = null

    /**make component's lifecycle to activityScope*/
    fun initComponent(context: Context): AppComponent {
        if (component == null) {
            require(context is Application) {
                "Passed context must me an instance of Application, but it is not!"
            }
            component = DaggerAppComponent
                .builder()
                .provideContext(context)
                .build()
        }

        return checkNotNull(component)
    }

    fun getComponent() = checkNotNull(component)

    fun clearComponent() {
        component = null
    }
}
