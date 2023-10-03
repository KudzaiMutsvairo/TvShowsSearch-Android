package com.kudzai.mutswairo.tvshows.feature.tvshow.domain.usecase

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository.TvShowsRepository
import javax.inject.Inject

class SearchTvShowUseCase @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
) {
    suspend operator fun invoke(query: String) = tvShowsRepository.searchTvShow(query)
}
