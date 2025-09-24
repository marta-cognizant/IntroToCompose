package com.example.introtocompose.repository

import com.example.introtocompose.data.WeatherDao
import com.example.introtocompose.model.Favorite
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavourites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavourite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavourites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavourite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)
}