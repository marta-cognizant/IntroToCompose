package com.example.introtocompose.screens.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.introtocompose.widgets.WeatherAppBar

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) { paddingValues ->
        Text(text = "Settings",
            modifier = Modifier.padding(paddingValues))
    }
}