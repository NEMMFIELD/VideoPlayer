package com.example.videoplayer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.data.network.ApiState
import com.example.videoplayer.domain.models.ModelDTO
import com.example.videoplayer.domain.usecase.LoadVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val getVideoUseCase: LoadVideosUseCase) :
    ViewModel() {
    internal var videoPath = ""
    private val _videoStateFlow: MutableStateFlow<ApiState<List<ModelDTO>>> =
        MutableStateFlow(ApiState.Empty)
    val videoStateFlow: StateFlow<ApiState<List<ModelDTO>>>
        get() = _videoStateFlow

    init {
        loadVideos()
    }

    private fun loadVideos() {
        viewModelScope.launch {
            try {
                getVideoUseCase.execute().collect { videos ->
                    _videoStateFlow.value = ApiState.Success(videos)
                }
            } catch (e: java.lang.Exception) {
                _videoStateFlow.value = ApiState.Failure(e)
            }
        }
    }
}