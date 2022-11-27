package com.example.videoplayer.domain.models

import com.example.videoplayer.data.models.VideoResponseItem

data class ModelDTO(
    val fileUrl: String?,
    val posterUrl: String?,
) {
    companion object {
        fun convertToDTO(response: VideoResponseItem?): ModelDTO {
            return ModelDTO(fileUrl = response?.fileUrl,
                posterUrl = response?.smallPosterUrl)
        }
    }
}