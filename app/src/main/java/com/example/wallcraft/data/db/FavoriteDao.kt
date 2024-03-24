package com.example.wallcraft.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(entity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM ${Constants.FAVORITE_TABLE} WHERE id ==:id)")
    fun isFavorite(id: String): LiveData<Boolean>

    @Query("SELECT * FROM ${Constants.FAVORITE_TABLE} WHERE  id ==:id")
    fun findTableId(id: String): FavoriteEntity

    @Query("SELECT * FROM ${Constants.FAVORITE_TABLE}")
    fun getAllFavorite(): Flow<MutableList<FavoriteEntity>>
}