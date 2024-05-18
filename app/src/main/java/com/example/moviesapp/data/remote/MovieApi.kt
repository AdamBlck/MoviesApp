package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.dto.MovieDetailDto
import com.example.moviesapp.data.remote.dto.MoviesDto
import com.example.moviesapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apikey :String = API_KEY
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apikey :String = API_KEY
    ) :MovieDetailDto
}