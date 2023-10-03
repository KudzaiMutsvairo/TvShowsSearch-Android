package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search

sealed class SearchEvents {
    class SubmitQuery(val query: String) : SearchEvents()
    object ClearMessages : SearchEvents()
}
