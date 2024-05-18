package com.example.moviesapp.domain.use_case.get_movies.views

import androidx.compose.ui.graphics.vector.DefaultStrokeLineMiter
import com.example.moviesapp.data.remote.dto.Search
import com.example.moviesapp.data.remote.dto.toMovieList
import com.example.moviesapp.data.repository.MovieRepositories
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repositories: MovieRepositories) {

    fun executeGetMovie (search: String) :Flow<Resource<List<Movie>>> = flow{
        try {
        emit(Resource.Loading())
            val movieList = repositories.getMovies(search)
            if (movieList.Response.equals("True")){
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error(message = "There is no movie"))
            }
        }catch (e :IOError){
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}