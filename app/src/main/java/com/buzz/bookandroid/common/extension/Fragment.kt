package com.buzz.bookandroid.common.extension

import android.util.Log
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController

internal const val TAG = "Extension-TAG"

internal fun <T> Fragment.setNavigationResult(@IdRes id: Int, key: String, value: T) {
    backStackEntry(id)?.savedStateHandle?.set(key, value)
}

private fun Fragment.backStackEntry(@IdRes id: Int) = try {
    findNavController().getBackStackEntry(id)
} catch (e: IllegalArgumentException) {
    Log.e(TAG, "backStackEntry: $e")
    null
}

internal fun <T> Fragment.onNavigationResult(
    @IdRes id: Int, key: String,
    onResult: (result: T) -> Unit
) {
    backStackEntry(id)?.let {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME
                && it.savedStateHandle.contains(key)
            ) {
                val result = it.savedStateHandle.get<T>(key)
                result?.let(onResult)
                it.savedStateHandle.remove<T>(key)
            }
        }
        it.lifecycle.addObserver(observer)

        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                it.lifecycle.removeObserver(observer)
            }
        })
    }
}
