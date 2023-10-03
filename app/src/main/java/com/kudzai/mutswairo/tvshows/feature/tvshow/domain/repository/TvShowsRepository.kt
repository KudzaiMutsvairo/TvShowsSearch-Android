package com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    suspend fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>
    suspend fun getTvShow(id: Long): Flow<Resource<TvShow?>>
}
