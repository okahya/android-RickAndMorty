package com.okahya.rickandmorty.base.ui.popup

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PopupModel(
    var title: Int? = null,
    var message: Int? = null,
    var cancelable: Boolean = false,
    var positiveButtonText: Int? = null,
    var negativeButtonText: Int? = null
) : Parcelable