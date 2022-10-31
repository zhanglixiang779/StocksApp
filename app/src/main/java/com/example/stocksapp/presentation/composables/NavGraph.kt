package com.example.stocksapp.presentation.composables

import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
enum class StocksButton(val content: String) : Parcelable {

    SuccessButton("Success"),
    EmptyButton("Empty"),
    ErrorButton("Error");

    companion object ButtonType : NavType<StocksButton>(isNullableAllowed = false) {
        override fun get(bundle: Bundle, key: String): StocksButton? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): StocksButton {
            return Gson().fromJson(value, StocksButton::class.java)
        }

        override fun put(bundle: Bundle, key: String, value: StocksButton) {
            bundle.putParcelable(key, value)
        }
    }
}

enum class Destination {
    Main, Stocks
}

private const val BUTTON_TYPE_KEY = "button_type_key"

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Main.name) {
        composable(route = Destination.Main.name) {
            MainScreen { button ->
                navController.navigate("${Destination.Stocks.name}/${button}")
            }
        }

        composable(
            route = "${Destination.Stocks.name}/{$BUTTON_TYPE_KEY}",
            arguments = listOf(navArgument(BUTTON_TYPE_KEY) { type = StocksButton.ButtonType })
        ) { backStackEntry ->
            StocksScreen(
                buttonType = backStackEntry.arguments?.getParcelable(BUTTON_TYPE_KEY)
                    ?: StocksButton.SuccessButton
            )
        }
    }
}