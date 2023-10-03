package com.kudzai.mutswairo.tvshows.feature.tvshow.data.repository

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.api.TvMazeApi
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.mapper.toTvShow
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository.TvShowsRepository
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val tvMazeApi: TvMazeApi,
) : TvShowsRepository {
    override suspend fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = tvMazeApi.searchTvShows(query)
                if (response.isSuccessful) {
                    val responseData = response.body()
                    val data = responseData?.let {
                        it.map { tvShowDto ->
                            tvShowDto.toTvShow()
                        }
                    } ?: run {
                        emptyList()
                    }
                    emit(
                        Resource.Success(
                            data,
                        ),
                    )
                } else {
                    emit(Resource.Error(message = "Failed to Fetch Data"))
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                emit(Resource.Error(message = ioException.message ?: "Error fetching Data"))
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(Resource.Error(message = "Unknown Error occurred"))
            }
        }
    }

    override suspend fun getTvShow(id: Long): Flow<Resource<TvShow?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = tvMazeApi.getShowById(id)
                if (response.isSuccessful) {
                    val responseData = response.body()
                    println("TvShow response ${response.body()}")
                    emit(
                        Resource.Success(
                            responseData?.toTvShow(),
                        ),
                    )
                } else {
                    emit(Resource.Error(message = "Failed to Fetch Data"))
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                emit(Resource.Error(message = ioException.message ?: "Error fetching Data"))
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(Resource.Error(message = "Unknown Error occurred"))
            }
        }
    }
}
