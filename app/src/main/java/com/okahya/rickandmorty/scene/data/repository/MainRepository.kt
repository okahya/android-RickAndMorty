package com.okahya.rickandmorty.scene.data.repository

import com.okahya.rickandmorty.base.network.remote.service.AppService
import javax.inject.Inject
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.okahya.rickandmorty.base.network.remote.repository.BaseRepository
import com.okahya.rickandmorty.scene.data.source.CharactersPagingDataSource
import kotlinx.coroutines.flow.Flow
import com.okahya.rickandmorty.scene.data.model.response.Character
import com.okahya.rickandmorty.scene.domain.GetEpisodeInformationUseCase.EpisodeInformationRequest
import com.okahya.rickandmorty.scene.ui.detail.uimodel.EpisodeUiModel
import kotlinx.coroutines.flow.flow

class MainRepository @Inject constructor(
    private val service: AppService,
): BaseRepository() {

    fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {CharactersPagingDataSource(service) }
        ).flow
    }

    fun getEpisodeInformation(episodeNumber: EpisodeInformationRequest): Flow<EpisodeUiModel> = flow {
        val result: MutableList<String> = mutableListOf()

        for (item in episodeNumber.episodeNumbers) {
            with(service.getEpisodeInformation(item)) {
                result.add("$name - $episode")
            }
        }

        emit(EpisodeUiModel(result))
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
