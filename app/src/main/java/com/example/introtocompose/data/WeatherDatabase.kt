package com.example.introtocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.introtocompose.model.Favorite
import com.example.introtocompose.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}