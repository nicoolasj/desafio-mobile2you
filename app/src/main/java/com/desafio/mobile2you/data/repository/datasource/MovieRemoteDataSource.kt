package com.desafio.mobile2you.data.repository.datasource

import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovie(): Response<Movie>
    suspend fun getSimilarMovies(): Response<SimilarMovies>
}
