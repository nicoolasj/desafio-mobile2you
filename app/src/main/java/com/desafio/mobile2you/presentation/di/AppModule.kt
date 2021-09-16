package com.desafio.mobile2you.presentation.di

import com.desafio.mobile2you.data.api.TMDBService
import com.desafio.mobile2you.data.repository.MovieRepositoryImpl
import com.desafio.mobile2you.data.repository.datasource.MovieRemoteDataSource
import com.desafio.mobile2you.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import com.desafio.mobile2you.domain.repository.MovieRepository
import com.desafio.mobile2you.util.Constants.API_KEY
import com.desafio.mobile2you.util.Constants.LANGUAGE
import com.desafio.mobile2you.util.Constants.MOVIE_ID
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
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(TMDBService::class.java)

    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        tmdbService: TMDBService,
    ): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(
            tmdbService,
            MOVIE_ID,
            API_KEY,
            LANGUAGE
        )

    @Provides
    @Singleton
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource)
}
