package com.example.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.introtocompose.model.Note
import com.example.introtocompose.screen.NoteScreen
import com.example.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val notes = remember {
                        mutableStateListOf<Note>()
                    }
                    NoteScreen(notes = notes, onAddNote = {
                        notes.add(it)
                    }, onRemoveNote = {
                        notes.remove(it)
                    })
                }
            }
        }
    }
}

@Composable
fun Greetings(text: String) {
    Text(text = "Hello $text!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToComposeTheme {
        Greetings("Android")
    }
}