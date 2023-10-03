package com.kudzai.mutswairo.tvshows.feature.tvshow.data.mapper

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.Show
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.Image
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow

fun Show.toTvShow(): TvShow {
    val htmlTagsRegex = "<.*?>".toRegex()
    return TvShow(
        score = null,
        averageRuntime = averageRuntime ?: 0,
        ended = ended,
        averageRating = rating?.average,
        genres = genres,
        id = id,
        image = Image(
            medium = image?.medium,
            original = image?.original,
        ),
        language = language,
        name = name,
        officialSite = officialSite,
        premiered = premiered,
        runtime = runtime ?: 0,
        status = status,
        summary = summary?.replace(htmlTagsRegex, ""),
        type = type,
        updated = updated ?: 0,
        url = url,
    )
}
