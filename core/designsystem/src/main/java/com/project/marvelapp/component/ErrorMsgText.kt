package com.project.marvelapp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun ErrorMessageHolder(
    msg: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = msg, textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = TextUnit.Unspecified
        )
    }
}