package com.example.videoplayer.data.repository

import com.example.videoplayer.data.network.ApiRequest
import com.example.videoplayer.domain.models.ModelDTO
import com.example.videoplayer.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepositoryImpl(private val api: ApiRequest) : Repository {

    override fun loadVideos(): Flow<List<ModelDTO>> = flow {
        val original = api.getVideos()
        val myList = original.map { ModelDTO.convertToDTO(it) }
        println("MY List : ${myList.size}")
        emit(myList)
    }.flowOn(Dispatchers.IO)
}