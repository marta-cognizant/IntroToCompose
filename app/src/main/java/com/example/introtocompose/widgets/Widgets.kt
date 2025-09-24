package com.example.introtocompose.widgets

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.introtocompose.R
import com.example.introtocompose.model.Weather
import com.example.introtocompose.model.WeatherItem
import com.example.introtocompose.utils.formatDate
import com.example.introtocompose.utils.formatDateTime
import com.example.introtocompose.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                formatDate(weather.dt)
                    .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )
            WeatherStateImage(imageUrl = imageUrl)
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    weather.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(alpha = 0.7f),
                        fontWeight = FontWeight.SemiBold,
                    )
                ) {
                    append(formatDecimals(weather.temp.max) + "°")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.SemiBold,
                    )
                ) {
                    append(formatDecimals(weather.temp.min) + "°")
                }
            })

        }

    }
}

@Composable
fun SunsetSunriseRow(weather: Weather) {
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weather.list[0].sunrise),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset",
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = formatDateTime(weather.list[0].sunset),
                style = MaterialTheme.typography.bodyMedium
            )
        }
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
                text = "${weather.list[0].humidity}%", style = MaterialTheme.typography.bodyMedium
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
                text = "${weather.list[0].speed} mph", style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {

    AsyncImage(
        model = imageUrl,
        contentDescription = "icon image",
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