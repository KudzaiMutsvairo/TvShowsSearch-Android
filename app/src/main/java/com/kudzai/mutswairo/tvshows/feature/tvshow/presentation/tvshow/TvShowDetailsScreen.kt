package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.tvshow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kudzai.mutswairo.tvshows.R
import com.kudzai.mutswairo.tvshows.common.components.LoadingComponent

@Composable
fun TvShowDetailsScreen(
    uiState: TvShowDetailsUiState,
    onEvent: (TvShowDetailsEvents) -> Unit,
    onBackNavigation: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            IconButton(onClick = { onBackNavigation() }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(
                        R.string.back,
                    ),
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoadingComponent(
                    progressCaption = stringResource(R.string.loading_tv_show),
                )
            }
        } else {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = uiState.data?.image?.medium,
                    contentDescription = "${uiState.data?.name} Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp)),
                )

                Text(
                    text = uiState.data?.name ?: "",
                    fontSize = 20.sp,
                )

                Text(
                    text = "Average Rating: ${uiState.data?.averageRating}",
                    fontSize = 14.sp,
                )

                Text(
                    text = "Language: ${uiState.data?.language}",
                    fontSize = 14.sp,
                )

                uiState.data?.genres?.let {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        items(it) {
                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = it,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }

                Text(
                    text = uiState.data?.summary ?: "",
                    fontSize = 14.sp,
                )
            }
        }

        if (uiState.message != null) {
            AlertDialog(
                onDismissRequest = {
                    onEvent(
                        TvShowDetailsEvents.ClearMessages,
                    )
                },
                title = { Text(text = "TvShows") },
                text = { Text(text = uiState.message ?: "Error occurred") },
                confirmButton = {
                    Button(
                        onClick = {
                            onEvent(
                                TvShowDetailsEvents.ClearMessages,
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
