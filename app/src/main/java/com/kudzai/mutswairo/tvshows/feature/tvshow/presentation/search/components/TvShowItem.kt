package com.kudzai.mutswairo.tvshows.feature.tvshow.presentation.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kudzai.mutswairo.tvshows.R
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow

@Composable
fun TvShowItem(
    tvShow: TvShow,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        AsyncImage(
            model = tvShow.image?.medium,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp)),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = tvShow.name ?: "",
                fontSize = 18.sp,
            )
            Text(
                fontSize = 13.sp,
                text = stringResource(id = R.string.rating) + ": ${tvShow.averageRating}",
            )
            Text(
                fontSize = 13.sp,
                text = stringResource(id = R.string.language) + ": ${tvShow.language}",
            )
            LazyRow {
                tvShow.genres?.let {
                    items(tvShow.genres) {
                        Text(
                            fontSize = 12.sp,
                            text = it,
                        )
                    }
                }
            }
        }
    }
}
