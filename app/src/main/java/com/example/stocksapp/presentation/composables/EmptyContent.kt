package com.example.stocksapp.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.stocksapp.R

@Composable
fun EmptyContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            text = stringResource(id = R.string.empty_content)
        )
    }
}