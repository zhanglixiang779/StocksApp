package com.example.stocksapp.data.models

data class Stock(
    val ticker: String,
    val name: String,
    val currency: String,
    val current_price_cents: Int,
    val quantity: Int? = null,
    val current_price_timestamp: Int
)