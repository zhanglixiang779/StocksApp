package com.example.stocksapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.stocksapp.presentation.composables.StocksButton.SuccessButton
import com.example.stocksapp.presentation.composables.StocksButton.EmptyButton
import com.example.stocksapp.presentation.composables.StocksButton.ErrorButton

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onButtonClicked: (StocksButton) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onButtonClicked(ErrorButton) }) {
            Text(style = MaterialTheme.typography.button, text = ErrorButton.content)
        }

        Button(onClick = { onButtonClicked(EmptyButton) }) {
            Text(style = MaterialTheme.typography.button, text = EmptyButton.content)
        }

        Button(onClick = { onButtonClicked(SuccessButton) }) {
            Text(style = MaterialTheme.typography.button, text = SuccessButton.content)
        }
    }
}