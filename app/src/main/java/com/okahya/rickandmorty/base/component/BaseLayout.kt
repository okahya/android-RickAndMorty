package com.okahya.rickandmorty.base.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.extension.loadImage

abstract class BaseLayout <VDB: ViewDataBinding?> @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int = 0
): ConstraintLayout(context, attributesSet, defStyleAttr, defStyleRes) {

    val binding: VDB by lazy {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        DataBindingUtil.inflate<VDB>(inflater, getLayoutResId(), this, true)
    }

    var clickListener: (view: View?) -> Unit = { }

    init {
        this.onInitialized(context, attributesSet, defStyleAttr)
    }

    protected fun setText(view: View?, @StringRes value: Int?) {
        (view as TextView).setText(value ?: 0)
    }

    protected fun setText(view: View?, value: String?) {
        (view as TextView).text = value
    }

    protected fun setImageFromUrl(view: View?, url: String?,
                                  @DrawableRes placeholderRes: Int? = null,
                                  @DrawableRes errorRes: Int? = null) {
        val defaultDrawable = R.drawable.ic_launcher_foreground

        (view as ImageView).loadImage(
            url,
            placeholderRes ?: defaultDrawable,
            errorRes ?: defaultDrawable)
    }

    protected fun setImageResource(view: View?, @DrawableRes image: Int?) {
        if (view is ImageView) {
            view.setImageResource(image ?: 0)
        }
    }

    protected abstract fun onInitialized(
        context: Context,
        attributesSet: AttributeSet?,
        defStyleAttr: Int
    )

    @LayoutRes
    protected abstract fun getLayoutResId(): Int
}