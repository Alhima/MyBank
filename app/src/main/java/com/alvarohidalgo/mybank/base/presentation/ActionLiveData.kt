package com.alvarohidalgo.mybank.base.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ActionLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }
        super.observe(owner, Observer { data ->
            data?.let {
                observer.onChanged(data)
                value = null
            }
        })

    }

    fun sendAction(data: T) {
        value = data
    }
}