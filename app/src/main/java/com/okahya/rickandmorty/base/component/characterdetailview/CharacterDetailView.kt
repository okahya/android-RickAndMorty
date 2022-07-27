package com.okahya.rickandmorty.base.component.characterdetailview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.okahya.rickandmorty.R
import com.okahya.rickandmorty.base.component.BaseLayout
import com.okahya.rickandmorty.databinding.ComponentCharacterDetailBinding
import com.okahya.rickandmorty.scene.data.type.Gender

class CharacterDetailView @JvmOverloads constructor(
    context: Context,
    attributesSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): BaseLayout<ComponentCharacterDetailBinding?>(context, attributesSet, defStyleAttr) {

    override fun getLayoutResId(): Int {
        return R.layout.component_character_detail
    }

    override fun onInitialized(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) {
        binding?.run {

        }
    }

    fun setCharacterName(@StringRes name: Int?) {
        setText(binding?.textViewName, name)
    }

    fun setCharacterName(name: String?) {
        setText(binding?.textViewName, name)
    }

    fun setCharacterStatusSpecies(status: String?, species: String?) {
        setText(binding?.textViewSpeciesStatus, "$status, $species")
    }

    fun setCharacterGender(@StringRes gender: Int?) {
        setText(binding?.textViewGender, gender)
    }

    fun setCharacterGender(gender: Gender?) {
        setText(binding?.textViewGender, gender?.name)
    }

    fun setCharacterImage(url: String?) {
        setImageFromUrl(binding?.imageViewCharacter, url)
    }

    fun getCharacterName(): TextView? {
        return binding?.textViewName
    }

    fun getCharacterStatusSpecies(): TextView? {
        return binding?.textViewSpeciesStatus
    }

    fun getCharacterGender(): TextView? {
        return binding?.textViewGender
    }

    fun getCharacterImage(): ImageView? {
        return binding?.imageViewCharacter
    }
}