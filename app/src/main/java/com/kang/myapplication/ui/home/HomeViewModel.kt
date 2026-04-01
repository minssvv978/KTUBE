package com.kang.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kang.myapplication.LangTubeApplication
import com.kang.myapplication.data.model.Video
import com.kang.myapplication.data.remote.VideoApiService
import com.kang.myapplication.data.repository.LanguageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val videos: List<Video>, val selectedLanguage: String?) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

class HomeViewModel(
    private val videoApiService: VideoApiService,
    private val languageRepository: LanguageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeLanguageAndFetchVideos()
    }

    private fun observeLanguageAndFetchVideos() {
        viewModelScope.launch {
            languageRepository.selectedLanguage.collectLatest { language ->
                fetchVideos(language)
            }
        }
    }

    fun fetchVideos(language: String?) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val videoList = videoApiService.getVideos(language = language)
                _uiState.value = HomeUiState.Success(videoList.videos, language)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LangTubeApplication)
                HomeViewModel(
                    videoApiService = application.container.videoApiService,
                    languageRepository = application.container.languageRepository
                )
            }
        }
    }
}
