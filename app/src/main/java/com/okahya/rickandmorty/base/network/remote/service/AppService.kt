package com.okahya.rickandmorty.base.network.remote.service

import com.okahya.rickandmorty.scene.data.model.response.CharacterListResponse
import com.okahya.rickandmorty.scene.data.model.response.EpisodeInformationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): CharacterListResponse

    @GET("episode/{episode_number}")
    suspend fun getEpisodeInformation(
        @Path("episode_number") episodeNumber: Int
    ): EpisodeInformationResponse
}