package com.example.stocksapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stocksapp.data.models.Stock

@Composable
fun SuccessContent(
    stocks: List<Stock>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = stocks, key = { stock -> stock.name }) { stock ->
            StockItem(stock)
        }
    }
}