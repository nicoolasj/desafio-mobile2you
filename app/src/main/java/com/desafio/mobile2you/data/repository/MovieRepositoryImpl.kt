package com.desafio.mobile2you.data.repository

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val tmdbService: TMDBService) :
    MovieRepository {
    override suspend fun getMovie(movieId: String): Movie = tmdbService.getMovie(movieId).body()!!

    override suspend fun getSimilarMovies(movieId: String): SimilarMovies =
        tmdbService.getSimilarMovies(movieId).body()!!
}