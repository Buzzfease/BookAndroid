package com.buzz.bookandroid.common.arch.view

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

internal interface BaseRenderer : LifecycleObserver {

    @MainThread
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear()
}