package com.example.moviesapp.domain.use_case.get_movie_details.views

import com.example.moviesapp.data.remote.dto.toMovieDetail
import com.example.moviesapp.data.repository.MovieRepositories
import com.example.moviesapp.domain.model.MovieDetail
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private  val repositories: MovieRepositories) {

    fun executeGetMovieDetail (imdbId: String) : Flow<Resource<MovieDetail>> = flow{
        try {
            emit(Resource.Loading())
            val movieDetail =repositories.getMovieDetail(imdbid = imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }catch (e : IOError){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}