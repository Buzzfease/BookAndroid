package com.buzz.bookandroid.common.arch.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged

class StateLiveData<STATE> : MutableLiveData<STATE>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in STATE>) {
        distinctUntilChanged().observe(owner, observer)
    }
}