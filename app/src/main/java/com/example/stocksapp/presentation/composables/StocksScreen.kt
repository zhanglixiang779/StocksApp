package com.example.stocksapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.stocksapp.data.NetworkResult.Loading
import com.example.stocksapp.data.NetworkResult.Success
import com.example.stocksapp.data.NetworkResult.Error
import com.example.stocksapp.presentation.StocksViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun StocksScreen(viewModel: StocksViewModel) {
    var shouldShowIndicator by remember {
        mutableStateOf(false)
    }
    when (val state = viewModel.stocks.collectAsStateWithLifecycle(initialValue = Loading()).value) {
        is Success -> {
            val apiStocks = state.data
            if (apiStocks != null && apiStocks.stocks.isNotEmpty()) {
                SuccessContent(apiStocks.stocks)
            } else {
                EmptyContent()
            }

            shouldShowIndicator = false
        }
        is Error -> {
            ErrorContent()
            shouldShowIndicator = false
        }
        is Loading -> {
            shouldShowIndicator = true
        }
    }

    ProgressIndicator(shouldShowIndicator)
}