package com.kudzai.mutswairo.tvshows.feature.tvshow.data.api

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.SearchResultsResponse
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.Show
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {

    @GET("search/shows")
    suspend fun searchTvShows(@Query("q") q: String): Response<SearchResultsResponse>

    @GET("shows/{id}")
    suspend fun getShowById(@Path("id") id: Long): Response<Show>
}
