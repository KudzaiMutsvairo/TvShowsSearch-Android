package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kudzai.mutswairo.tvshows.R
import com.kudzai.mutswairo.tvshows.common.components.LoadingComponent
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search.components.SearchBar
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search.components.TvShowItem
import com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow.TvShowDetailsEvents

@Composable
fun SearchScreen(
    uiState: UiStateSearch,
    onEvent: (SearchEvents) -> Unit,
    onNavigateToTvShowEvent: (TvShow) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        SearchBar(onSearch = {
            onEvent(SearchEvents.SubmitQuery(it))
        })
        Spacer(modifier = Modifier.height(10.dp))
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoadingComponent(
                    progressCaption = stringResource(R.string.searching),
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(uiState.data) { tvShow: TvShow ->
                    TvShowItem(
                        tvShow = tvShow,
                        modifier = Modifier.height(80.dp),
                        onClick = {
                            onNavigateToTvShowEvent(tvShow)
                        },
                    )
                }
            }
        }

        if (uiState.message != null) {
            AlertDialog(
                onDismissRequest = {
                    onEvent(
                        SearchEvents.ClearMessages,
                    )
                },
                title = { Text(text = "TvShows") },
                text = { Text(text = uiState.message ?: "Error occurred") },
                confirmButton = {
                    Button(
                        onClick = {
                            onEvent(
                                SearchEvents.ClearMessages,
                            )
                        },
                    ) {
                        Text(text = "OK")
                    }
                },
                modifier = Modifier.fillMaxSize().wrapContentSize(),
            )
        }
    }
}
