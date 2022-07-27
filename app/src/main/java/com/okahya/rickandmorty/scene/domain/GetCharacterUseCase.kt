package com.okahya.rickandmorty.scene.domain

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.okahya.rickandmorty.base.network.remote.domain.FlowUseCase
import com.okahya.rickandmorty.base.network.remote.domain.None
import com.okahya.rickandmorty.scene.data.model.response.Character
import com.okahya.rickandmorty.scene.data.repository.MainRepository
import com.okahya.rickandmorty.scene.ui.home.uimodel.CharacterUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: MainRepository
) : FlowUseCase<None, PagingData<Pair<Character, CharacterUiModel>>>() {

    override fun buildUseCase(params: None): Flow<PagingData<Pair<Character, CharacterUiModel>>> {
        return repository
            .getAllCharacters()
            .cachedIn(CoroutineScope(Dispatchers.Main))
            .map { pagingData ->
                pagingData.map {
                    Pair(it,
                        CharacterUiModel(
                            it.name,
                            it.image
                        )
                    )
                }
            }
    }
}