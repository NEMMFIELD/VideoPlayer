package com.example.videoplayer.domain.repository

import com.example.videoplayer.domain.models.ModelDTO
import kotlinx.coroutines.flow.Flow

interface Repository {
     fun loadVideos(): Flow<List<ModelDTO>>
}