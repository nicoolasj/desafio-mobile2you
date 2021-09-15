package com.desafio.mobile2you.data.api

import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBService {

    @GET("/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: String): Response<Movie>

    @GET("/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: String): Response<SimilarMovies>
}