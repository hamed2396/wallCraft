package com.example.wallcraft.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wallcraft.data.db.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class,],
    version = 4,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): FavoriteDao
}