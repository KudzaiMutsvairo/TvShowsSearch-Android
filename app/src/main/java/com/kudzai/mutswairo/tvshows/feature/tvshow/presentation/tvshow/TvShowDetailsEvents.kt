package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow

sealed class TvShowDetailsEvents {
    class OnInitialize(val id: Long) : TvShowDetailsEvents()
    object ClearMessages : TvShowDetailsEvents()
}
