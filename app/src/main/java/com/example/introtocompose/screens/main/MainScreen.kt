package com.example.introtocompose.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.introtocompose.data.DataOrException
import com.example.introtocompose.model.Weather

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    ShowData(mainViewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
            value = mainViewModel.getWeatherData(city = "Seattle")
        }.value

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        Text("Main Screen: ${weatherData.data!!.toString()}")
    }

}