package com.example.moviesapp.presantation.theme.movieDetails

import com.example.moviesapp.domain.model.MovieDetail
import com.example.moviesapp.util.Resource

data class MovieDetailState(

    val isLoading: Boolean =false,
    val movie :MovieDetail? =null,
    val error : String = ""
){

}