package com.example.moviesapp.presantation.theme.movies.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.moviesapp.domain.model.Movie


@Composable
fun MovieListRow(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {
Row (modifier = Modifier
    .fillMaxWidth()
    .clickable {
        onItemClick(movie)
    }
    .padding(5.dp),
    horizontalArrangement = Arrangement.Absolute.Left
){
    val customShape = GenericShape { size, _ ->
        moveTo(size.width / 2, 0f)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }

        Card(
            modifier = Modifier.size(190.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.Green,
                containerColor = Color.Gray
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = movie.Poster),
                contentDescription = movie.Title,
                contentScale = ContentScale.Crop, // Bu, görüntüyü kırpmadan sığdırır
                modifier = Modifier
                    .fillMaxSize() // Kartın tüm alanını kaplar
            )
        }



    Column (modifier =Modifier.align(Alignment.CenterVertically).padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = movie.Title,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        Text(text = movie.Year,
            style = MaterialTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            textAlign = TextAlign.Center
        )

    }

}

}