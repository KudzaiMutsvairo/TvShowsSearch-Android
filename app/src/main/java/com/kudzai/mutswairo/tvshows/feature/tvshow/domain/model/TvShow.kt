package com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model

data class TvShow(
    val score: Double?,
    val averageRuntime: Int?,
    val ended: String?,
    val averageRating: Double?,
    val genres: List<String>?,
    val id: Long,
    val image: Image?,
    val language: String?,
    val name: String?,
    val officialSite: String?,
    val premiered: String?,
    val runtime: Int?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
)
