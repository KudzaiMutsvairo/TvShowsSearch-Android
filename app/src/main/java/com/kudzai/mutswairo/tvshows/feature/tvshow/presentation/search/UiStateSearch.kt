package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow

data class UiStateSearch(
    var isLoading: Boolean = false,
    var data: List<TvShow> = emptyList(),
    var message: String? = null,
)
