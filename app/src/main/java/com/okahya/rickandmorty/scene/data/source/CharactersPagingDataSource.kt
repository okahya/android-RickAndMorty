package com.okahya.rickandmorty.scene.data.source

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.okahya.rickandmorty.base.network.remote.service.AppService
import com.okahya.rickandmorty.scene.data.model.response.Character

class CharactersPagingDataSource (
    private val apiService: AppService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>):  LoadResult<Int, Character> {
        return try {
            val page = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getAllCharacters(page)
            var nextPageNumber: Int? = null

            if (response.info.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(data = response.results ?: emptyList(), prevKey = null, nextKey = nextPageNumber)

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}