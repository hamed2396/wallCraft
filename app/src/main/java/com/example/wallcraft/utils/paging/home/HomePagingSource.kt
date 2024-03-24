package com.example.wallcraft.utils.paging.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallcraft.data.models.home.ResponsePhotos
import com.example.wallcraft.data.network.ApiServices

import javax.inject.Inject

class HomePagingSource@Inject constructor(
    private val api: ApiServices,
    private val id: String
) : PagingSource<Int, ResponsePhotos.responsePhotosItem>() {
    private var pageIndex = 1
    override fun getRefreshKey(state: PagingState<Int, ResponsePhotos.responsePhotosItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponsePhotos.responsePhotosItem> {
        val position = params.key ?: pageIndex
        return try {
            val response = api.getHomePhotos(id, position)
            val data = response.body()!!

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