package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow

data class TvShowDetailsUiState(
    var isLoading: Boolean = false,
    var data: TvShow? = null,
    var isError: Boolean = false,
    var message: String? = null,
)
