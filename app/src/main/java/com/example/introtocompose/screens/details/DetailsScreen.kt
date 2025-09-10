@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.introtocompose.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.introtocompose.model.Movie
import com.example.introtocompose.model.getMovies
import com.example.introtocompose.widgets.MovieRow

@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().first { movie -> movie.id == movieId }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(movie.title)
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.LightGray
            ), navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(horizontal = 12.dp))
            })
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = movie)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Movie Images", style = MaterialTheme.typography.headlineMedium)
                HorizontalScrollableImageView(movie = movie)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Movie image",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
