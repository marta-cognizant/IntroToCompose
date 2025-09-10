@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.introtocompose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.introtocompose.R
import com.example.introtocompose.components.NoteInputText

@Composable
fun NoteScreen() {
    Column() {
        TopAppBar(
            title = { Text(
                text = stringResource(id = R.string.app_name),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
             },
            actions = {
                Icon(Icons.Rounded.Create, contentDescription = "App Icon")
            },
            colors = TopAppBarDefaults.topAppBarColors(titleContentColor = Color(0xFFDADFE3))
        )
        // Content
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                text = "Hello",
                label = "Hello",
                onTextChange = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen()
}