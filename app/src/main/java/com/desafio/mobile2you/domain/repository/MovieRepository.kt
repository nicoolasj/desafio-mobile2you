package com.desafio.mobile2you.domain.repository

import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies

interface MovieRepository {
    suspend fun getMovie(): Movie
    suspend fun getSimilarMovies(): SimilarMovies
}
