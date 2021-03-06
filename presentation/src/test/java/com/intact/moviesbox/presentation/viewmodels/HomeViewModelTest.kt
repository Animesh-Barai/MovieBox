package com.intact.moviesbox.presentation.viewmodels

import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.PopularMoviesUseCase
import com.intact.moviesbox.presentation.mapper.NowPlayingDomainPresentationMapper
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var baseSchedulerProvider: BaseSchedulerProvider

    private lateinit var homeViewModel: HomeViewModel
    private val movieDataEntityMapper = NowPlayingDomainPresentationMapper()
    //private val usr = TestDataGenerator

    @Before
    fun setUp() {
        // Mocking all the required variables
        MockitoAnnotations.initMocks(this)

//        val popularMoviesUseCase =
//            PopularMoviesUseCase(movieRepository, baseSchedulerProvider)
    }
}