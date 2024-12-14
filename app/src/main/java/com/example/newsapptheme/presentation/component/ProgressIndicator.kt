package com.example.newsapptheme.presentation.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressIndicator(){
    Dialog(onDismissRequest = { }) {
        CircularProgressIndicator(color = Blue)
//        Text(text = "Loading")
    }
}