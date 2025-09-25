package com.example.introtocompose.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.introtocompose.model.Favorite
import com.example.introtocompose.model.Unit
import com.example.introtocompose.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
) : ViewModel() {
    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect { listOfUnits ->
                if (listOfUnits.isNullOrEmpty()) {
                    Log.d("TAG", "Empty units")
                    _unitList.value = emptyList<Unit>()
                } else {
                    _unitList.value = listOfUnits
                }
            }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        repository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        repository.updateUnit(unit)
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        repository.deleteUnit(unit)
    }

    fun deleteAllUnits() = viewModelScope.launch {
        repository.deleteAllUnits()
    }
}