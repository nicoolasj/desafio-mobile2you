package com.desafio.mobile2you.domain.usecase

import com.desafio.mobile2you.domain.repository.MovieRepository
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun execute() =
        repository.getSimilarMovies()
}
