package com.example.moviesapp.presantation.theme.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.dto.Search
import com.example.moviesapp.domain.use_case.get_movies.views.GetMovieUseCase
import com.example.moviesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val  getMovieUseCase: GetMovieUseCase) : ViewModel() {

    private val _state = mutableStateOf<MovieState>(MovieState())
    val state : State<MovieState> = _state

    private  var job : Job? = null

    init {
        getMovies(_state.value.search)
    }
    private fun getMovies(search: String){
        job?.cancel()

       job = getMovieUseCase.executeGetMovie(search).onEach {

           when(it){
               is Resource.Success -> {
                _state.value = MovieState(movies =it.data ?: emptyList() )
               }
               is Resource.Error -> {
                _state.value = MovieState(error = it.message ?: "Error!")
               }
               is Resource.Loading -> {
                _state.value = MovieState(isLoading = true)
               }
           }

        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }

}