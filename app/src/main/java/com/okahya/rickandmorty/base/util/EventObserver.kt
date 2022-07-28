package com.okahya.rickandmorty.base.util

import androidx.lifecycle.Observer

class EventObserver<T>(private val onChanged: (T) -> Unit) : Observer<Event<T>> {

    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
}