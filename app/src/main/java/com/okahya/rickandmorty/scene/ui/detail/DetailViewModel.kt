package com.okahya.rickandmorty.scene.ui.detail

import com.okahya.rickandmorty.base.ui.BaseViewModel
import com.okahya.rickandmorty.scene.MainViewModel
import com.okahya.rickandmorty.scene.domain.GetEpisodeInformationUseCase.EpisodeInformationRequest
import com.okahya.rickandmorty.scene.domain.GetEpisodeInformationUseCase
import com.okahya.rickandmorty.scene.ui.detail.uimodel.EpisodeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEpisodeInformationUseCase: GetEpisodeInformationUseCase
) : BaseViewModel() {

    private var activityViewModel: MainViewModel? = null

    fun setActivityViewModel(activityViewModel: MainViewModel) {
        this.activityViewModel = activityViewModel
    }

    fun getEpisode(episodeNumber: EpisodeInformationRequest): Flow<EpisodeUiModel> {
        return getEpisodeInformationUseCase.observe(
            EpisodeInformationRequest(episodeNumber.episodeNumbers)
        )
    }
}