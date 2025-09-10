@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.introtocompose.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.introtocompose.model.Movie
import com.example.introtocompose.model.getMovies
import com.example.introtocompose.navigation.MovieScreens
import com.example.introtocompose.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movies")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta
                ),
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()

) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) { item ->
                MovieRow(item) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}

