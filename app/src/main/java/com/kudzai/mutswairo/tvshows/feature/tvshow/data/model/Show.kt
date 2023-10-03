package com.kudzai.mutswairo.tvshows.feature.tvshow.data.model

data class Show(
    val averageRuntime: Int?,
    val ended: String?,
    val genres: List<String>?,
    val id: Long,
    val image: ImageDTO?,
    val language: String?,
    val name: String?,
    val network: NetworkDTO?,
    val officialSite: String?,
    val premiered: String?,
    val rating: RatingDTO?,
    val runtime: Int?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
)
