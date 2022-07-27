package com.okahya.rickandmorty.scene.data.model.response

import com.okahya.rickandmorty.scene.data.type.Gender

data class CharacterListResponse(
    val info: Info,
    val results: List<Character>? = emptyList()
)

data class Character(
    val id: Int,
    val name: String?,
    val species: String?,
    val status: String?,
    val image: String?,
    val gender: Gender?,
    val episode: List<String>?
)

data class Info(
    val count: Int?,
    val pages: String?,
    val next: String?,
    val prev: String?
)