package com.example.moviesapp.domain.repository

import android.os.Parcel
import android.os.Parcelable
import com.example.moviesapp.data.remote.MovieApi
import com.example.moviesapp.data.remote.dto.MovieDetailDto
import com.example.moviesapp.data.remote.dto.MoviesDto
import com.example.moviesapp.data.repository.MovieRepositories
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api : MovieApi) : MovieRepositories {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbid: String): MovieDetailDto {
        return api.getMovieDetails(imdbId = imdbid)
    }



}