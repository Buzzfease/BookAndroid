package com.buzz.bookandroid.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData

class TestLifeCircleOwner : LifecycleOwner {

    private val registry = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.RESUMED
    }

    override fun getLifecycle(): Lifecycle = registry


}

internal fun <T> LiveData<T>.observeValues(): List<T> {
    val data: MutableList<T> = mutableListOf()
    val testLifecycleOwner = TestLifeCircleOwner()
    observe(testLifecycleOwner) {
        data.add(it)
    }
    return data
}

