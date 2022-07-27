package com.okahya.rickandmorty.scene.ui.home

import androidx.paging.PagingData
import com.okahya.rickandmorty.base.network.remote.domain.None
import com.okahya.rickandmorty.base.network.remote.model.Character
import com.okahya.rickandmorty.base.ui.BaseViewModel
import com.okahya.rickandmorty.scene.domain.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
) : BaseViewModel() {


    fun getCharacters(): Flow<PagingData<Character>> {
        return getCharacterUseCase.observe(None)
    }
}