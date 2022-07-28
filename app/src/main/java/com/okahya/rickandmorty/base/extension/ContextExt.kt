package com.okahya.rickandmorty.base.extension

import android.content.Context
import com.okahya.rickandmorty.base.ui.popup.Popup
import com.okahya.rickandmorty.base.ui.popup.PopupListener
import com.okahya.rickandmorty.base.ui.popup.PopupModel

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}