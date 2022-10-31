package com.example.stocksapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class StocksButton(val content: String) {
    object SuccessButton : StocksButton("Success")

    object EmptyButton : StocksButton("Empty")

    object ErrorButton : StocksButton("Error")

    companion object {

        fun getButtonType(content: String): StocksButton {
            if (content == "Success") return SuccessButton
            if (content == "Empty") return EmptyButton
            if (content == "Error") return ErrorButton

            return SuccessButton
        }
    }
}

enum class Destination {
    Main, Stocks
}

private const val BUTTON_TYPE_KEY = "button_type"

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "${Destination.Main.name}/{$BUTTON_TYPE_KEY}") {
        composable(
            route = "${Destination.Main.name}/{$BUTTON_TYPE_KEY}",
            arguments = listOf(navArgument(BUTTON_TYPE_KEY) { type = NavType.StringType })
        ) {
            MainScreen { button ->
                navController.navigate("${Destination.Stocks.name}/${button.content}")
            }
        }

        composable("${Destination.Stocks.name}/{$BUTTON_TYPE_KEY}") { backStackEntry ->
            StocksScreen(
                button = backStackEntry.arguments?.getString(BUTTON_TYPE_KEY) ?: StocksButton.SuccessButton.content
            )
        }
    }
}