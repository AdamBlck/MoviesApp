package com.example.moviesapp.presantation.theme.movies

import com.example.moviesapp.data.remote.dto.Search


sealed class MoviesEvent {
    data class Search (val searchString : String) : MoviesEvent()
}