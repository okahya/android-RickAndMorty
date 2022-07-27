package com.okahya.rickandmorty.scene.domain

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.okahya.rickandmorty.base.network.remote.domain.FlowUseCase
import com.okahya.rickandmorty.base.network.remote.domain.None
import com.okahya.rickandmorty.base.network.remote.model.Character
import com.okahya.rickandmorty.scene.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: MainRepository
) : FlowUseCase<None, PagingData<Character>>() {

    override fun buildUseCase(params: None): Flow<PagingData<Character>> {
        return repository.getAllCharacters().cachedIn(CoroutineScope(Dispatchers.Main))
    }
}