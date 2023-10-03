package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.usecase.GetTvShowByIdUseCase
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val getTvShowByIdUseCase: GetTvShowByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TvShowDetailsUiState())
    val uiState: StateFlow<TvShowDetailsUiState> = _uiState

    fun onEvent(events: TvShowDetailsEvents) {
        when (events) {
            is TvShowDetailsEvents.OnInitialize -> {
                getTvShowData(events.id)
            }

            is TvShowDetailsEvents.ClearMessages -> {
                clearAllMessages()
            }
        }
    }

    private fun getTvShowData(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getTvShowByIdUseCase(id).collect { result ->
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
                                isError = true,
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
                }
            }
        }
    }

    private fun clearAllMessages() {
        _uiState.update {
            it.copy(
                isError = false,
                message = null
            )
        }
    }
}
