package com.example.wallcraft.utils.di



import com.example.wallcraft.utils.base.BaseDiffUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {
    @Module
    @InstallIn(ActivityComponent::class)
    object AdapterModule {
        @Provides
        fun provideBaseDiffUtil(): BaseDiffUtil<Any> =
            BaseDiffUtil()

    }
}