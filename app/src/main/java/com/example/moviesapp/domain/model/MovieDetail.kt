package com.example.moviesapp.domain.model

import com.example.moviesapp.data.remote.dto.Rating

data class MovieDetail(
    val Actors: String,
    val Awards: String,
    val Country: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Plot: String,
    val Poster: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    val imdbRating: String
)
