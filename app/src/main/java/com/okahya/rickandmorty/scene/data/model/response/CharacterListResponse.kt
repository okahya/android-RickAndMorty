package com.okahya.rickandmorty.base.network.remote.model

data class CharacterListResponse(
    val info: Info,
    val results: List<Character>? = emptyList()
)

data class Character(
    val id: Int,
    val name: String?,
    val species: String?,
    val type: String?,
    val image: String?,
    val url: String?,
    val episode: List<String>?
)

data class Info(
    val count: Int?,
    val pages: String?,
    val next: String?,
    val prev: String?
)