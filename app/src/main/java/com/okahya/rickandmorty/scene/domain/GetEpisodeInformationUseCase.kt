package com.okahya.rickandmorty.scene.domain

import com.okahya.rickandmorty.base.network.remote.domain.FlowUseCase
import com.okahya.rickandmorty.scene.data.repository.MainRepository
import com.okahya.rickandmorty.scene.ui.detail.uimodel.EpisodeUiModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetEpisodeInformationUseCase @Inject constructor(
    private val repository: MainRepository
) : FlowUseCase<GetEpisodeInformationUseCase.EpisodeInformationRequest, EpisodeUiModel>() {

    override fun buildUseCase(params: EpisodeInformationRequest): Flow<EpisodeUiModel> {
        return repository
            .getEpisodeInformation(params)
            .map { it }
    }

    data class EpisodeInformationRequest(
        val episodeNumbers: MutableList<Int>
    )
}