package com.example.videoplayer.data.models

import com.squareup.moshi.Json

data class VideoResponse(

	@Json(name = "VideoResponse")
	val videoResponse: List<VideoResponseItem?>? = null,
)

data class VideoResponseItem(

	@Json(name = "file_url")
	val fileUrl: String? = null,

	@Json(name = "small_poster_url")
	val smallPosterUrl: String? = null,

	@Json(name = "is_favorite")
	val isFavorite: Boolean? = null,

	@Json(name = "color")
	val color: Any? = null,

	@Json(name = "poster_url")
	val posterUrl: String? = null,

	@Json(name = "small_thumbnail_url")
	val smallThumbnailUrl: String? = null,

	@Json(name = "id")
	val id: String? = null,

	@Json(name = "thumbnail_url")
	val thumbnailUrl: String? = null,

	@Json(name = "group")
	val group: String? = null,
)
