package com.example.moviesapp.presantation.theme.movieDetails.views

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.moviesapp.R
import com.example.moviesapp.presantation.theme.movieDetails.MovieDetailViewModel
import com.example.moviesapp.util.Constants.IMDB_ID


@Composable
fun MovieDetailScreen (movieDetailViewModel: MovieDetailViewModel = hiltViewModel()){

    val state =movieDetailViewModel.state.value

    val stateForScrol = rememberScrollState()
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        contentAlignment = Alignment.Center
        ){
        state.movie?.let {
            Column (
                Modifier.verticalScroll(stateForScrol),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                
                Row (modifier = Modifier
                    .padding(7.dp),
                    horizontalArrangement = Arrangement.Absolute.Center,
                    verticalAlignment = Alignment.CenterVertically
                    ){
                    Text(text = "Imdb Rating: ${it.imdbRating}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(14.dp),
                        color = Color.White
                    )
                    if (it.Awards.isNotBlank()){
                        Image(imageVector = Icons.Default.Star, contentDescription = "award",
                            modifier = Modifier.background(Color.Yellow))
                    }
                    
                }
                
                Image(painter = rememberImagePainter(it.Poster), contentDescription =it.Title,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(350.dp, 300.dp)
                        .clip(RectangleShape)
                        .align(Alignment.CenterHorizontally)
                )
                Text(text = it.Title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White
                )
                Text(text = it.Plot,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White
                )
                Text(text = "Actors : ${it.Actors}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White
                )
                Text(text = "Genre : ${it.Genre}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White
                )
                Text(text = "Awards : ${it.Awards}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(14.dp),
                    color = Color.White
                )
            }
        }
        if (state.error.isNotBlank()){
            Text(text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
                    .align(Alignment.Center)
                )
        }
        if (state.isLoading){
            //CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}








