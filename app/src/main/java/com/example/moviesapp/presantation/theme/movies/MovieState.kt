package com.example.moviesapp.presantation.theme.movies

import com.example.moviesapp.domain.model.Movie


data class MovieState(
    val isLoading :Boolean = false,
    val movies : List<Movie> = emptyList(),
    val error  : String = "",
    val search : String = "Batman"
)