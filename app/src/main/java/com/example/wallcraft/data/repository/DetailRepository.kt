package com.example.wallcraft.data.repository

import com.example.wallcraft.data.db.FavoriteDao
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val api: ApiServices,
    private val dao: FavoriteDao
) {
    suspend fun getDetailPhoto(id: String) = api.getDetailPhoto(id)

    //db
    suspend fun insertFavorite(entity: FavoriteEntity) = dao.insertFavorite(entity)


    suspend fun deleteFavorite(entity: FavoriteEntity)= dao.deleteFavorite(entity)


    fun isFavorite(id: String)=dao.isFavorite(id)
    fun findTableId(id: String)=dao.findTableId(id)


}