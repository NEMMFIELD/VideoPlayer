package com.example.videoplayer.domain.usecase

import com.example.videoplayer.domain.models.ModelDTO
import com.example.videoplayer.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadVideosUseCase @Inject constructor (private val repository: Repository) {

    fun execute(): Flow<List<ModelDTO>> {
        return repository.loadVideos()
    }
}