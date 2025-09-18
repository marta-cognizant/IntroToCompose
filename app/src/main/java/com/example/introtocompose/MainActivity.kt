package com.example.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.introtocompose.screens.TriviaHome
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                TriviaHome()
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToComposeTheme {

    }
}