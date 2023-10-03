package com.kudzai.mutswairo.tvshows.feature.tvshow.domain.usecase

import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.model.TvShow
import com.kudzai.mutswairo.tvshows.feature.tvshow.domain.repository.TvShowsRepository
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetTvShowByIdUseCaseTest {
    private lateinit var useCase: GetTvShowByIdUseCase
    private var repository: TvShowsRepository = mockk<TvShowsRepository>()

    @Before
    fun setUp() {
        useCase = GetTvShowByIdUseCase(repository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN GetTvShowByIdUseCase is successful THEN return success`() = runTest {
        // Arrange
        val expectedShow = TvShow(
            score = null,
            averageRuntime = null,
            ended = null,
            averageRating = null,
            genres = null,
            id = 1L,
            image = null,
            language = null,
            name = null,
            officialSite = null,
            premiered = null,
            runtime = null,
            status = null,
            summary = null,
            type = null,
            updated = null,
            url = null,
        )

        coEvery { repository.getTvShow(1L) } returns flow {
            emit(Resource.Loading())
            emit(Resource.Success(expectedShow))
        }

        // Act
        val resultFlow = useCase(1L)
        val result = resultFlow.toList()

        // Assert
        assertTrue(result.first() is Resource.Loading)
        assertTrue(result.last() is Resource.Success)
        assertEquals(expectedShow, (result.last() as Resource.Success).data)
    }

    @Test
    fun `GIVEN GetTvShowByIdUseCase is unsuccessful THEN return error`() = runTest {
        // Arrange
        val expectedError = "Error"
        coEvery { repository.getTvShow(1L) } returns flow {
            emit(Resource.Loading())
            emit(Resource.Error(message = expectedError))
        }

        // Act
        val resultFlow = useCase(1L)
        val result = resultFlow.toList()

        // Assert
        assertTrue(result.first() is Resource.Loading)
        assertTrue(result.last() is Resource.Error)
        assertEquals(expectedError, (result.last() as Resource.Error).message)
    }
}
