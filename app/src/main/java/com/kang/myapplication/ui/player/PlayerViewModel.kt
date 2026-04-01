package com.kang.myapplication.ui.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kang.myapplication.LangTubeApplication
import com.kang.myapplication.data.model.Video
import com.kang.myapplication.data.remote.VideoApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PlayerUiState {
    object Loading : PlayerUiState
    data class Success(val video: Video) : PlayerUiState
    data class Error(val message: String) : PlayerUiState
}

class PlayerViewModel(
    private val videoApiService: VideoApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    fun fetchVideoDetails(videoId: String) {
        viewModelScope.launch {
            _uiState.value = PlayerUiState.Loading
            try {
                val video = videoApiService.getVideo(videoId)
                _uiState.value = PlayerUiState.Success(video)
            } catch (e: Exception) {
                _uiState.value = PlayerUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LangTubeApplication)
                PlayerViewModel(
                    videoApiService = application.container.videoApiService
                )
            }
        }
    }
}
