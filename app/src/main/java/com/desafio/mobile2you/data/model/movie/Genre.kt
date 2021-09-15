package com.desafio.mobile2you.data.model.movie


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String
)