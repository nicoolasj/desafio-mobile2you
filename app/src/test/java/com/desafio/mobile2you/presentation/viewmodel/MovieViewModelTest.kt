package com.desafio.mobile2you.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.desafio.mobile2you.data.model.movie.Genre
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.data.repository.datasourceimpl.FakeMovieRepository
import com.desafio.mobile2you.domain.usecase.GetMovieUseCase
import com.desafio.mobile2you.domain.usecase.GetSimilarMoviesUseCase
import getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMovieUseCase = GetMovieUseCase(fakeMovieRepository)
        val getSimilarMoviesUseCase = GetSimilarMoviesUseCase(fakeMovieRepository)
        movieViewModel = MovieViewModel(getMovieUseCase, getSimilarMoviesUseCase)
    }

    @Test
    fun getMovie_returnsCurrentMovie() {
        val genre = mutableListOf(Genre("name"))
        val movie = Movie(
            "backdropPath",
            genre,
            1,
            "overview",
            100.0,
            "posterPath",
            "tagline",
            "title",
            1
        )
        val currentMovie = movieViewModel.getMovie().getOrAwaitValue()
        assertThat(currentMovie).isEqualTo(movie)
    }

    @Test
    fun getSimilarMovies_returnsSimilarMovies() {
        val similarMovie = mutableListOf(SimilarMovie("posterPath", "2000", "title"))
        val similarMovies = SimilarMovies(similarMovie)
        val currentSimilarMovies = movieViewModel.getSimilarMovies().getOrAwaitValue()
        assertThat(currentSimilarMovies).isEqualTo(similarMovies)
    }
}