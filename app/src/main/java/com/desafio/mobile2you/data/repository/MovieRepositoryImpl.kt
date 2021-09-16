package com.desafio.mobile2you.data.repository

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.data.repository.datasource.MovieRemoteDataSource
import com.desafio.mobile2you.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovie(): Movie = movieRemoteDataSource.getMovie().body()!!

    override suspend fun getSimilarMovies(): SimilarMovies =
        movieRemoteDataSource.getSimilarMovies().body()!!
}