package com.okahya.rickandmorty.scene.data.type

import com.squareup.moshi.Json

enum class Gender {
    @Json(name= "Male")
    MALE,
    @Json(name= "Female")
    FEMALE,
    @Json(name= "Genderless")
    GENDERLESS,
    @Json(name= "unknown")
    UNKNOWN
}