package com.example.moviesapp.presantation.theme.screen

sealed class Screen(val route: String) {
    object MovieScreen :Screen("movie_screen")
    object MovieDetailScreen :Screen("Movie_detail_screen")
}