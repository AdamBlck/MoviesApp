package com.example.moviesapp.data.repository

import com.example.moviesapp.data.remote.dto.MovieDetailDto
import com.example.moviesapp.data.remote.dto.MoviesDto
import com.example.moviesapp.data.remote.dto.Search

interface MovieRepositories {
    suspend fun getMovies(search: String):MoviesDto
    suspend fun getMovieDetail(imdbid :String) : MovieDetailDto

}