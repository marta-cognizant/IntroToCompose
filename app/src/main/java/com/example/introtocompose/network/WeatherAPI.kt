package com.example.introtocompose.network

import com.example.introtocompose.model.Weather
import com.example.introtocompose.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("appid") appid: String = Constants.API_KEY,
        @Query("units") units: String = "imperial"
    ): Weather
}