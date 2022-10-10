package com.example.stocksapp.data

import com.example.stocksapp.data.retrofit.StockService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val stockService: StockService
) {
    suspend fun getStocks() = stockService.getStocks()

    suspend fun getEmptyStocks() = stockService.getEmptyStocks()

    suspend fun getErrorStocks() = stockService.getErrorStocks()
}