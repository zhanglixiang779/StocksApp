package com.example.stocksapp.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.stocksapp.R
import com.example.stocksapp.data.models.Stock

@Composable
fun StockItem(
    stock: Stock,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        Row {
            Text(text = stringResource(id = R.string.symbol))
            Text(text = stock.ticker)
        }

        Row {
            Text(text = stringResource(id = R.string.name))
            Text(text = stock.name)
        }

        Row {
            Text(text = stringResource(id = R.string.price))
            Text(text = "$${stock.current_price_cents / 100}.${stock.current_price_cents % 100}")
        }

        Row {
            Text(text = stringResource(id = R.string.quantity))
            Text(text = stock.quantity?.run { "${stock.quantity}" } ?: "0")
        }

        Spacer(modifier = modifier.padding(vertical = 4.dp))
        Divider()
    }
}