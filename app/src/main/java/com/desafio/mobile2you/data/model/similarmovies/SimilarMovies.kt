package com.desafio.mobile2you.data.model.similarmovies

import com.google.gson.annotations.SerializedName

data class SimilarMovies(
    @SerializedName("results")
    val similarMovies: List<SimilarMovie>
)
