package com.okahya.rickandmorty.scene.ui.home

import androidx.paging.PagingData
import com.okahya.rickandmorty.base.network.remote.domain.None
import com.okahya.rickandmorty.scene.data.model.response.Character
import com.okahya.rickandmorty.base.ui.BaseViewModel
import com.okahya.rickandmorty.scene.MainViewModel
import com.okahya.rickandmorty.scene.domain.GetCharacterUseCase
import com.okahya.rickandmorty.scene.ui.home.uimodel.CharacterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : BaseViewModel() {

    private var activityViewModel: MainViewModel? = null

    fun setActivityViewModel(activityViewModel: MainViewModel) {
        this.activityViewModel = activityViewModel
    }

    fun getCharacters(): Flow<PagingData<Pair<Character, CharacterUiModel>>> {
        return getCharacterUseCase.observe(None)
    }
}