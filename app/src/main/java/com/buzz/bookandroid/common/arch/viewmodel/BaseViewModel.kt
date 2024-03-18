package com.buzz.bookandroid.common.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.buzz.bookandroid.common.arch.intent.Action
import com.buzz.bookandroid.common.arch.livedata.SingleLiveEvent
import com.buzz.bookandroid.common.arch.livedata.StateLiveData
import com.buzz.bookandroid.common.arch.model.ViewEvent
import com.buzz.bookandroid.common.arch.model.ViewState

internal abstract class BaseViewModel<A : Action, S : ViewState, E : ViewEvent> : ViewModel() {

    private val thisState by lazy { StateLiveData<S>() }
    private val thisEvent by lazy { SingleLiveEvent<E>() }

    val state: LiveData<S> get() = thisState
    val event: LiveData<E> get() = thisEvent

    protected fun emitState(state: S) {
        thisState.value = state
    }

    protected fun emitEvent(event: E) {
        thisEvent.value = event
    }

    fun doAction(action: A) {
        onAction(action = action, currentState = state.value)
    }

    protected abstract fun onAction(action: A, currentState: S?)
}
