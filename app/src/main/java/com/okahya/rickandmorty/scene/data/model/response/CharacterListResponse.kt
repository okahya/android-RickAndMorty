package com.okahya.rickandmorty.scene.data.model.response

import android.os.Parcelable
import com.okahya.rickandmorty.scene.data.type.Gender
import kotlinx.parcelize.Parcelize

data class CharacterListResponse(
    val info: Info,
    val results: List<Character>? = emptyList()
)

@Parcelize
data class Character(
    val id: Int,
    val name: String?,
    val species: String?,
    val status: String?,
    val image: String?,
    val gender: Gender?,
    val episode: List<String>?
): Parcelable

data class Info(
    val count: Int?,
    val pages: String?,
    val next: String?,
    val prev: String?
)