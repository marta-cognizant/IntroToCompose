package com.example.introtocompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.introtocompose.screens.about.AboutScreen
import com.example.introtocompose.screens.favourit.FavoritesScreen
import com.example.introtocompose.screens.main.MainScreen
import com.example.introtocompose.screens.main.MainViewModel
import com.example.introtocompose.screens.search.SearchScreen
import com.example.introtocompose.screens.settings.SettingsScreen
import com.example.introtocompose.screens.splashscreen.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) {navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController, mainViewModel, city = city)
            }
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController)
        }
        composable(WeatherScreens.FavoritesScreen.name) {
            FavoritesScreen(navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController)
        }
    }
}