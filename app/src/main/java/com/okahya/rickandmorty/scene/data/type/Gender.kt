package com.okahya.rickandmorty.scene.data.type

import com.squareup.moshi.Json

enum class Gender {
    @Json(name= "Male")
    Male,
    @Json(name= "Female")
    Female,
    @Json(name= "Genderless")
    Genderless,
    @Json(name= "unknown")
    Unknown
}