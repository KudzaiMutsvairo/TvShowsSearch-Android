package com.kudzai.mutswairo.tvshows.feature.tvshow.data.mapper

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.TvShowDTO
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.Image
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow

fun TvShowDTO.toTvShow(): TvShow {
    return TvShow(
        score = score,
        averageRuntime = show.averageRuntime ?: 0,
        ended = show.ended,
        averageRating = show.rating?.average,
        genres = show.genres,
        id = show.id,
        image = Image(
            medium = show.image?.medium,
            original = show.image?.original,
        ),
        language = show.language,
        name = show.name,
        officialSite = show.officialSite,
        premiered = show.premiered,
        runtime = show.runtime ?: 0,
        status = show.status,
        summary = show.summary,
        type = show.type,
        updated = show.updated ?: 0,
        url = show.url,
    )
}
