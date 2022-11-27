package com.example.videoplayer.data.network

import com.example.videoplayer.data.models.VideoResponse
import com.example.videoplayer.data.models.VideoResponseItem
import retrofit2.http.GET


interface ApiRequest {
    @GET("api/backgrounds/?group=video&category_id=1")
    suspend fun getVideos(): List<VideoResponseItem>
}