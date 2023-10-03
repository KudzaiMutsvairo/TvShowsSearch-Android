package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.usecase.SearchTvShowUseCase
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val searchTvShowUseCase: SearchTvShowUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStateSearch())
    val uiState: StateFlow<UiStateSearch> = _uiState

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.SubmitQuery -> {
                searchQuery(event.query)
            }

            is SearchEvents.ClearMessages -> {
                clearAllMessages()
            }

            else -> {}
        }
    }

    private fun searchQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchTvShowUseCase(query).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                message = result.message,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                data = result.data,
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun clearAllMessages() {
        _uiState.update {
            it.copy(
                message = null,
            )
        }
    }
}
