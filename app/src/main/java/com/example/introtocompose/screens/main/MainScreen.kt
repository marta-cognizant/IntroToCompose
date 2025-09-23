package com.example.introtocompose.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.introtocompose.R
import com.example.introtocompose.data.DataOrException
import com.example.introtocompose.model.Weather
import com.example.introtocompose.utils.formatDate
import com.example.introtocompose.utils.formatDecimals
import com.example.introtocompose.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "Thorn")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + ", ${weather.city.country}",
            icon = Icons.Default.ArrowBack,
            navController = navController,
            elevation = 5.dp,
        ) {
            Log.d("WEATHER_APP_BAR", "ButtonClicked!")
        }
    }) { paddingValues ->
        Scaffold(modifier = Modifier.padding(paddingValues)) {
            MainContent(data = weather)
        }
    }
}

@Composable
fun MainContent(data: Weather) {
    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    val temperature = formatDecimals(data.list[0].temp.day) + "°"
    val description = data.list[0].weather[0].main
    Log.d("WEATHER_APP", "Image URL: $imageUrl")

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(data.list[0].dt),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            // color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = temperature, style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = description, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weather = data)
        Divider()
    }
}

@Composable
fun HumidityWindPressureRow(weather: Weather) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.list[0].humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.list[0].pressure} psi",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.list[0].speed} mph",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {

    AsyncImage(
        model = imageUrl, contentDescription = "icon image",
        modifier = Modifier.size(80.dp),
        onLoading = {
            Log.d("ASYNC_IMAGE", "Loading...")
        },
        onSuccess = {
            Log.d("ASYNC_IMAGE", "Success...")
        },
        onError = {
            Log.d("ASYNC_IMAGE", "Error... ${it.result.throwable}")
            Log.d("ASYNC_IMAGE", "${it.result.throwable.message}")
            Log.d("ASYNC_IMAGE", "${it.result.throwable.cause}")
        },
        placeholder = painterResource(R.drawable.sun),
        error = painterResource(R.drawable.sun)

    )

}