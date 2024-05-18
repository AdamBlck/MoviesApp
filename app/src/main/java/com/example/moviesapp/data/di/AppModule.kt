package com.example.moviesapp.data.di

import com.example.moviesapp.data.remote.MovieApi
import com.example.moviesapp.data.repository.MovieRepositories
import com.example.moviesapp.domain.repository.MovieRepositoryImpl
import com.example.moviesapp.util.Constants.BASE_URL
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
    fun provideMovieApp(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository ( api: MovieApi) : MovieRepositories{
        return MovieRepositoryImpl(api = api)
    }
}