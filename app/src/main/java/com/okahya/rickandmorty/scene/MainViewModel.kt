package com.okahya.rickandmorty.scene

import com.okahya.rickandmorty.base.ui.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okahya.rickandmorty.base.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {


    private val _toolbar = MutableLiveData<Event<Boolean>>()
    val toolbar: LiveData<Event<Boolean>> = _toolbar

    private val _loading = MutableLiveData(Event(false))
    val loading: LiveData<Event<Boolean>> = _loading


    fun loading(state: Boolean? = null) {
        state?.let {
            _loading.value = Event(it)
        }
    }

    fun toolbar(state: Boolean? = null) {
        state?.let {
            _toolbar.value = Event(it)
        }
    }
}