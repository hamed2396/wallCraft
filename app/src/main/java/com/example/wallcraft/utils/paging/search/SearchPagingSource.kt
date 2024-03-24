package com.example.wallcraft.utils.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallcraft.data.models.home.ResponsePhotos
import com.example.wallcraft.data.models.search.ResponseSearch
import com.example.wallcraft.data.network.ApiServices

import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val api: ApiServices,
    private val id: String
) : PagingSource<Int,ResponseSearch.Result>() {
    private var pageIndex = 1
    override fun getRefreshKey(state: PagingState<Int, ResponseSearch.Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseSearch.Result> {
        val position = params.key ?: pageIndex
        return try {
            val response = api.getSearchPhotos(id, position)
            val data = response.body()!!.results!!

            LoadResult.Page(
                data = data,
                prevKey = if (position == pageIndex) null else position.minus(1),
                nextKey = if (data.isEmpty()) null else position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}