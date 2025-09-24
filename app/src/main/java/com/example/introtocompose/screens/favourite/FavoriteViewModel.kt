package com.example.introtocompose.screens.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introtocompose.model.Favorite
import com.example.introtocompose.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().collect { listOfFavs ->
                if (listOfFavs.isNullOrEmpty()) {
                    _favList.value = emptyList<Favorite>()
                    Log.d("FAVS", ": Empty favourites list")
                } else {
                    _favList.value = listOfFavs
                    Log.d("FAVS", "Favourites list: ${favList.value}")
                }
            }

        }
    }
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.updateFavourite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.deleteFavourite(favorite)
    }

    fun deleteAllFavorites() = viewModelScope.launch {
        repository.deleteAllFavourites()
    }
}