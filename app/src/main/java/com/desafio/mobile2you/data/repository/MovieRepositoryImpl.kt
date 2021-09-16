package com.desafio.mobile2you.data.repository

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val tmdbService: TMDBService,
) :
    MovieRepository {
    override suspend fun getMovie(movieId: String, apiKey: String, language: String): Movie =
        tmdbService.getMovie(movieId, apiKey, language).body()!!

    override suspend fun getSimilarMovies(
        movieId: String,
        apiKey: String,
        language: String
    ): SimilarMovies =
        tmdbService.getSimilarMovies(movieId, apiKey, language).body()!!
}