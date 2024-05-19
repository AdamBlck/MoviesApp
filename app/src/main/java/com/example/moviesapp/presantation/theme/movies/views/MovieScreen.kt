package com.example.moviesapp.presantation.theme.movies.views

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesapp.presantation.theme.movies.MovieViewModel
import com.example.moviesapp.presantation.theme.movies.MoviesEvent
import com.example.moviesapp.presantation.theme.screen.Screen
import com.example.moviesapp.util.Constants.IMDB_ID

@SuppressLint("SuspiciousIndentation")
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel()
){
  val state = viewModel.state.value

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Column {
        MovieSearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
            hint = "Batman",
            onSearch = {
                viewModel.onEvent(MoviesEvent.Search(it))
            })
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.movies){ movie->
                    MovieListRow(movie = movie, onItemClick = {
                        if (movie.imdbID.isEmpty()){

                        }else{
                            IMDB_ID = movie.imdbID
                            navController.navigate(Screen.MovieDetailScreen.route)
                        }


                    })
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint.isNotEmpty()) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(5.dp, CircleShape)
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                isHintDisplayed = newText.isEmpty()
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearch(text)
                    focusManager.clearFocus() // Odaktan çık
                    keyboardController?.hide() // Klavyeyi gizle
                }
            ),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                Color.Transparent, // Arka planı şeffaf yapıyoruz
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent, // Alt çizgiyi kaldırıyoruz
                unfocusedIndicatorColor = Color.Transparent // Alt çizgiyi kaldırıyoruz
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isHintDisplayed = !focusState.isFocused && text.isEmpty()
                }
        )

        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

