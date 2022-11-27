package com.example.videoplayer.di

import com.example.videoplayer.domain.repository.Repository
import com.example.videoplayer.domain.usecase.LoadVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLoadVideoUseCase(repository: Repository):LoadVideosUseCase {
        return LoadVideosUseCase(repository = repository)
    }
}