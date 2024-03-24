package com.example.wallcraft.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wallcraft.data.network.ApiServices
import com.example.wallcraft.utils.paging.home.PhotosByOrderPagingSource
import javax.inject.Inject

class ExploreRepository @Inject constructor(private val api: ApiServices) {

    //paging
    fun photosByOrder(order: String) = Pager(
        config = PagingConfig(1, maxSize = 100,  enablePlaceholders = false),
        pagingSourceFactory = { PhotosByOrderPagingSource(api, order) }
    ).liveData
}