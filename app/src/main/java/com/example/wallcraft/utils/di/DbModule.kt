package com.example.wallcraft.utils.di

import android.content.Context
import androidx.room.Room
import com.example.wallcraft.data.db.AppDataBase
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()


    @Provides
    @Singleton
    fun provideDao(db: AppDataBase) = db.dao()



}