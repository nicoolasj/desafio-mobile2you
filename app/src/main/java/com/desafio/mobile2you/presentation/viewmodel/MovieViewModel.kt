package com.desafio.mobile2you.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.domain.repository.MovieRepository
import com.desafio.mobile2you.domain.usecase.GetMovieUseCase
import com.desafio.mobile2you.domain.usecase.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    fun getMovie() = liveData {
        val movie = getMovieUseCase.execute()
        emit(movie)
    }

    fun getSimilarMovies() = liveData {
        val similarMovies = getSimilarMoviesUseCase.execute()
        emit(similarMovies)
    }
}