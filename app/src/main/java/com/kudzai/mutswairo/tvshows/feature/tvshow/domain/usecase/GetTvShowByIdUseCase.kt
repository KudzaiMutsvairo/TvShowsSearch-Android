package com.kudzai.mutswairo.tvshows.feature.tvshow.domain.usecase

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository.TvShowsRepository
import javax.inject.Inject

class GetTvShowByIdUseCase @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
) {
    suspend operator fun invoke(id: Long) = tvShowsRepository.getTvShow(id)
}
