package com.example.introtocompose.repository

import android.util.Log
import com.example.introtocompose.data.DataOrException
import com.example.introtocompose.model.Weather
import com.example.introtocompose.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e: Exception) {
            Log.d("GET_WEATHER", "getWeather: $e")
            return DataOrException(e = e)
        }
        Log.d("GET_WEATHER", "getWeather: $response")
        return DataOrException(data = response)
    }
}