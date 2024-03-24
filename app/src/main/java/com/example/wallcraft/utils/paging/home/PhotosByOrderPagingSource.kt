package com.example.wallcraft.utils.paging.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wallcraft.data.models.explore.ResponsePhotosByOrder
import com.example.wallcraft.data.network.ApiServices

import javax.inject.Inject

class PhotosByOrderPagingSource@Inject constructor(
    private val api: ApiServices,
    private val order: String
) : PagingSource<Int, ResponsePhotosByOrder.ResponsePhotosByOrderItem>() {
    private var pageIndex = 1
    override fun getRefreshKey(state: PagingState<Int, ResponsePhotosByOrder.ResponsePhotosByOrderItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponsePhotosByOrder.ResponsePhotosByOrderItem> {
        val position = params.key ?: pageIndex
        return try {
            
            val response = api.getPhotosByOrder(order, position)
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