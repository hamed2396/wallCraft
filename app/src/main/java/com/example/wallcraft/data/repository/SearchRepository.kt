package com.example.wallcraft.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wallcraft.data.network.ApiServices
import com.example.wallcraft.utils.paging.home.HomePagingSource
import com.example.wallcraft.utils.paging.search.SearchPagingSource
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: ApiServices) {

    //paging
    fun searchPhoto(id: String) = Pager(
        config = PagingConfig(1, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { SearchPagingSource(api, id) }
    ).liveData
}