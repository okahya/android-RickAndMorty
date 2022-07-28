package com.okahya.rickandmorty.base.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.extension.loadImage


abstract class BaseCardView <VDB: ViewDataBinding?> @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int
) : CardView(context, attributeSet, defStyleAttr) {

    val binding: VDB by lazy {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        DataBindingUtil.inflate<VDB>(inflater, getLayoutResId(), this, true)
    }

    var clickListener: (view: View?) -> Unit = { }

    init {
        this.onInitialized(context, attributeSet, defStyleAttr)
    }

    protected fun setText(view: View?, @StringRes value: Int?) {
        (view as TextView).setText(value ?: 0)
    }

    protected fun setText(view: View?, value: String?) {
        (view as TextView).text = value
    }

    protected fun setImage(view: View?, @DrawableRes res: Int?) {
        (view as ImageView).setImageResource(res ?: 0)
    }

    protected fun setImage(view: View?, drawable: Drawable?) {
        (view as ImageView).setImageDrawable(drawable)
    }

    protected fun setImageFromUrl(view: View?, url: String?,
                                  @DrawableRes placeholderRes: Int? = null,
                                  @DrawableRes errorRes: Int? = null
    ) {
        val defaultDrawable = R.drawable.ic_launcher_foreground

        (view as ImageView).loadImage(
            url,
            placeholderRes ?: defaultDrawable,
            errorRes ?: defaultDrawable)
    }

    protected abstract fun onInitialized(
        context: Context,
        attributeSet: AttributeSet?,
        defStyleAttr: Int
    )

    @LayoutRes
    protected abstract fun getLayoutResId(): Int
}