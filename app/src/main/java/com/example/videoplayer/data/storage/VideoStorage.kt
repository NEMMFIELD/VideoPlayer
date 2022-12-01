package com.example.videoplayer.data.storage

import com.example.videoplayer.data.network.ApiRequest
import com.example.videoplayer.domain.models.ModelDTO
import kotlinx.coroutines.flow.Flow

interface VideoStorage {

    fun load(): Flow<List<ModelDTO>>
}