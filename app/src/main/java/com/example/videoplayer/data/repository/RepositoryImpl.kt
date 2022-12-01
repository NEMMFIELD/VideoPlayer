package com.example.videoplayer.data.repository


import com.example.videoplayer.data.storage.VideoStorage
import com.example.videoplayer.domain.models.ModelDTO
import com.example.videoplayer.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(private val videoStorage: VideoStorage) : Repository {
    override fun loadVideos(): Flow<List<ModelDTO>> {
        return videoStorage.load()
    }
}