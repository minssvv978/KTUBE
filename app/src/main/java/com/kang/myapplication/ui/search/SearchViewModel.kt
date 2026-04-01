package com.kang.myapplication.ui.search

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

sealed interface SearchUiState {
    object Idle : SearchUiState
    object Loading : SearchUiState
    data class Success(val videos: List<Video>) : SearchUiState
    data class Error(val message: String) : SearchUiState
}

class SearchViewModel(
    private val videoApiService: VideoApiService,
    private val languageRepository: LanguageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var currentLanguage: String? = null

    init {
        viewModelScope.launch {
            languageRepository.selectedLanguage.collectLatest { language ->
                currentLanguage = language
            }
        }
    }

    fun searchVideos(query: String) {
        if (query.isBlank()) {
            _uiState.value = SearchUiState.Idle
            return
        }

        viewModelScope.launch {
            _uiState.value = SearchUiState.Loading
            try {
                // Priority logic: The Mock API already filters by language if provided.
                // In a real app, we might fetch all and sort or rely on backend.
                val videoList = videoApiService.getVideos(language = currentLanguage, query = query)
                _uiState.value = SearchUiState.Success(videoList.videos)
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LangTubeApplication)
                SearchViewModel(
                    videoApiService = application.container.videoApiService,
                    languageRepository = application.container.languageRepository
                )
            }
        }
    }
}
