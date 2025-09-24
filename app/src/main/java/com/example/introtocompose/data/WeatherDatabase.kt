package com.example.introtocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.introtocompose.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}