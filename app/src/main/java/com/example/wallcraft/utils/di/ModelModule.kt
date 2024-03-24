package com.example.wallcraft.utils.di

import com.example.wallcraft.data.db.entity.FavoriteEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ModelModule {
    @Provides
    @FragmentScoped
    fun provideEntity() = FavoriteEntity()
}