package com.example.videoplayer.data.storage

import com.example.videoplayer.data.network.ApiRequest
import com.example.videoplayer.domain.models.ModelDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoadVideoStorage @Inject constructor(private val api: ApiRequest):VideoStorage {
    override fun load(): Flow<List<ModelDTO>> = flow {
        val original = api.getVideos()
        val myList = original.map { ModelDTO.convertToDTO(it) }
        emit(myList)
    }.flowOn(Dispatchers.IO)
}