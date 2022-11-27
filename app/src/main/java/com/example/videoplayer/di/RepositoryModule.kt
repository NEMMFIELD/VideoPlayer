package com.example.videoplayer.di

import com.example.videoplayer.data.network.ApiRequest
import com.example.videoplayer.data.repository.RepositoryImpl
import com.example.videoplayer.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(api: ApiRequest): Repository = RepositoryImpl(api)
}