package com.example.wallcraft.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallcraft.utils.Constants

@Entity(Constants.FAVORITE_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var tableId: Int=0,
    var id: String?=null,
    var image: String?=null,
    var blurHash: String?=null,

)
