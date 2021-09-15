package com.desafio.mobile2you.data.model.similarmovies


import com.google.gson.annotations.SerializedName

data class SimilarMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)