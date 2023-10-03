package com.kudzai.mutswairo.tvshows.feature.tvshow.data.repository

import com.kudzai.mutswairo.tvshows.feature.tvshow.data.api.TvMazeApi
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.SearchResultsResponse
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.Show
import com.kudzai.mutswairo.tvshows.feature.tvshow.data.model.TvShowDTO
import com.kudzai.mutswairo.tvshows.feature.tvshow.util.Resource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class TvShowsRepositoryImplTest {
    private lateinit var repository: TvShowsRepositoryImpl
    private val mockApi: TvMazeApi = mockk<TvMazeApi>()

    @Before
    fun setUp() {
        repository = TvShowsRepositoryImpl(mockApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN valid query THEN return Success`() = runTest {
        // Arrange
        val query = "top gear"
        val expectedData = emptyList<TvShowDTO>()
        val mockResponse = mockk<Response<SearchResultsResponse>>(relaxed = true)
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns SearchResultsResponse()

        coEvery { mockApi.searchTvShows(query) } returns mockResponse

        // Act
        val resultFlow = repository.searchTvShow(query)
        val resultList = resultFlow.toList()

        // Assert
        assertTrue(resultList.first() is Resource.Loading)
        assertTrue(resultList.last() is Resource.Success)
    }

    @Test
    fun `GIVEN request fails THEN return Error`() = runTest {
        // Arrange
        val query = "<##>"
        val expectedData = emptyList<TvShowDTO>()
        val mockResponse = mockk<Response<SearchResultsResponse>>(relaxed = true)

        every { mockResponse.isSuccessful } returns false
        every { mockResponse.body() } returns SearchResultsResponse()

        coEvery { mockApi.searchTvShows(query) } returns mockResponse

        // Act
        val resultFlow = repository.searchTvShow(query)
        val resultList = resultFlow.toList()

        // Assert
        assertTrue(resultList.first() is Resource.Loading)
        assertTrue(resultList.last() is Resource.Error)
    }

    @Test
    fun `GIVEN valid id for TvShow THEN return Success`() = runTest {
        // Arrange
        val id = 1L
        val expectedData = emptyList<TvShowDTO>()
        val mockResponse = mockk<Response<Show>>(relaxed = true)

        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns Show(
            averageRuntime = null,
            ended = null,
            genres = emptyList(),
            id = id,
            image = null,
            language = null,
            name = null,
            network = null,
            officialSite = null,
            premiered = null,
            rating = null,
            runtime = null,
            status = null,
            summary = null,
            type = null,
            updated = null,
            url = null,
        )

        coEvery { mockApi.getShowById(id) } returns mockResponse

        // Act
        val resultFlow = repository.getTvShow(id)
        val resultList = resultFlow.toList()

        // Assert
        assertTrue(resultList.first() is Resource.Loading)
        assertTrue(resultList.last() is Resource.Success)
    }

    @Test
    fun `GIVEN request is not successful WHEN getting TvShow THEN return Error`() = runTest {
        // Arrange
        val id = 1L
        val expectedData = emptyList<TvShowDTO>()
        val mockResponse = mockk<Response<Show>>(relaxed = true)

        every { mockResponse.isSuccessful } returns false
        coEvery { mockApi.getShowById(id) } returns mockResponse

        // Act
        val resultFlow = repository.getTvShow(id)
        val resultList = resultFlow.toList()

        // Assert
        assertTrue(resultList.first() is Resource.Loading)
        assertTrue(resultList.last() is Resource.Error)
    }
}
