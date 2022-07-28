package com.okahya.rickandmorty.base.ui.popup

class PopupListener(
    val onPositiveButtonClick: (() -> Unit)? = null,
    val onNegativeButtonClick: (() -> Unit)? = null
)