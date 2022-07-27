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

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
