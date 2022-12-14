package com.okahya.rickandmorty.base.component.charactercardview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.component.BaseCardView
import com.okahya.rickandmorty.databinding.ComponentCharacterBinding

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): BaseCardView<ComponentCharacterBinding?>(context, attributesSet, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.component_character
    }

    override fun onInitialized(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) {
        binding?.run {
            cardViewCharacterItem.setOnClickListener { clickListener(it) }
        }
    }

    fun setCharacterName(@StringRes name: Int?) {
        setText(binding?.textViewName, name)
    }

    fun setCharacterName(name: String?) {
        setText(binding?.textViewName, name)
    }

    fun setCharacterImage(url: String?) {
        setImageFromUrl(binding?.imageViewCharacter, url)
    }

    fun getCharacterName(): TextView? {
        return binding?.textViewName
    }

    fun getCharacterImage(): ImageView? {
        return binding?.imageViewCharacter
    }
}