package com.kudzai.mutswairo.tvshows.feature.tvshow.di

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.api.TvMazeApi
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.repository.TvShowsRepositoryImpl
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTvShowsRepository(api: TvMazeApi): TvShowsRepository {
        return TvShowsRepositoryImpl(api)
    }
}
