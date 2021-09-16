package com.desafio.mobile2you.data.repository.datasourceimpl

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.data.repository.datasource.MovieRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieId: String,
    private val apiKey: String,
    private val language: String
) : MovieRemoteDataSource {
    override suspend fun getMovie(): Response<Movie> =
        tmdbService.getMovie(movieId, apiKey, language)

    override suspend fun getSimilarMovies(): Response<SimilarMovies> =
        tmdbService.getSimilarMovies(movieId, apiKey, language)
}