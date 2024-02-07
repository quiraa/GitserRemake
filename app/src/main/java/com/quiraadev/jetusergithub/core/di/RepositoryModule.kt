package com.quiraadev.jetusergithub.core.di

import com.quiraadev.jetusergithub.core.data.local.FavoriteDao
import com.quiraadev.jetusergithub.core.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(dao: FavoriteDao): Repository = Repository(dao)
}