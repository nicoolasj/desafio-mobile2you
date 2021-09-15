package com.desafio.mobile2you.presentation.di

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.repository.MovieRepositoryImpl
import com.desafio.mobile2you.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTMDBService(): TMDBService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://developers.themoviedb.org/3")
            .build()
            .create(TMDBService::class.java)

    @Provides
    @Singleton
    fun provideMovieRepository(tmdbService: TMDBService): MovieRepository =
        MovieRepositoryImpl(tmdbService)
}