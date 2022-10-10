package com.example.stocksapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stocksapp.presentation.StocksViewModel

sealed class StocksButton(val content: String) {
    object SuccessButton : StocksButton("Success")

    object EmptyButton : StocksButton("Empty")

    object ErrorButton : StocksButton("Error")
}

enum class Destination {
    Main, Stocks
}

@Composable
fun NavGraph(
    viewModel: StocksViewModel,
    onButtonClicked: (StocksButton) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Main.name) {
        composable(Destination.Main.name) { MainScreen {
            onButtonClicked(it)
            navController.navigate(Destination.Stocks.name)
        } }

        composable(Destination.Stocks.name) { StocksScreen(viewModel) }
    }
}