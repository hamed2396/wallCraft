package com.example.wallcraft.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wallcraft.data.db.FavoriteDao
import com.example.wallcraft.data.network.ApiServices
import com.example.wallcraft.utils.paging.home.HomePagingSource
import com.example.wallcraft.utils.paging.search.SearchPagingSource
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FavoriteDao) {
    fun getAllFavorite()=dao.getAllFavorite()
}