package com.example.wallcraft.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wallcraft.data.network.ApiServices
import com.example.wallcraft.utils.paging.home.HomePagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getCategory() = api.getCategory()
    //paging
    fun homePhoto(id: String) = Pager(
        config = PagingConfig(1, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { HomePagingSource(api, id) }
    ).liveData
}