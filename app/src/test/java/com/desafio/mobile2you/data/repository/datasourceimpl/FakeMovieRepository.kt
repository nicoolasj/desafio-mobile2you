package com.desafio.mobile2you.data.repository.datasourceimpl

import com.desafio.mobile2you.data.model.movie.Genre
import com.desafio.mobile2you.data.model.movie.Movie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovie
import com.desafio.mobile2you.data.model.similarmovies.SimilarMovies
import com.desafio.mobile2you.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {

    private val movie: Movie
    private val genre = mutableListOf<Genre>()
    private val similarMovies: SimilarMovies
    private val similarMovie = mutableListOf<SimilarMovie>()

    init {
        genre.add(Genre("name"))
        movie = Movie(
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
        similarMovie.add(SimilarMovie("posterPath", "2000", "title"))
        similarMovies = SimilarMovies(similarMovie)
    }

    override suspend fun getMovie(): Movie {
        return movie
    }

    override suspend fun getSimilarMovies(): SimilarMovies {
        return similarMovies
    }
}
