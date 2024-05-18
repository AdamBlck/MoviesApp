package com.example.moviesapp.presantation.theme.movieDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.use_case.get_movie_details.views.GetMovieDetailsUseCase
import com.example.moviesapp.util.Constants.IMDB_ID
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase
, private val savedStateHandle: SavedStateHandle) :ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state



    init {
        IMDB_ID?.let { imdbId ->
            getMovieDetail(imdbId)
        }
    }

    private fun getMovieDetail(imdbId:String){
        getMovieDetailsUseCase.executeGetMovieDetail(imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value =MovieDetailState(movie = it.data)
                }
                is Resource.Loading ->{
                    _state.value = MovieDetailState(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = MovieDetailState(error = it.message ?:"Error!")
                }
            }
        }.launchIn(viewModelScope)

    }

}