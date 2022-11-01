package com.example.stocksapp.fake

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.data.models.Stock
import com.example.stocksapp.domain.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class FakeStockRepository : Repository {

    override suspend fun getStocks(): NetworkResult<ApiStocks> {
        val fakeStocks = listOf(
            Stock(
                ticker = "TWTR",
                name = "Twitter, Inc.",
                currency = "USD",
                current_price_cents = 999,
                quantity = null,
                current_price_timestamp = 1636657688
            ),
            Stock(
                ticker = "RUNINC",
                name = "Runners Inc.",
                currency = "USD",
                current_price_cents = 3614,
                quantity = 5,
                current_price_timestamp = 1636657677
            ),
        )

        return NetworkResult.Success(ApiStocks(fakeStocks))
    }

    override suspend fun getEmptyStocks(): NetworkResult<ApiStocks> {
        val emptyStock = emptyList<Stock>()
        return NetworkResult.Success(ApiStocks(emptyStock))
    }

    override suspend fun getErrorStocks(): NetworkResult<ApiStocks> {
        return NetworkResult.Error("something went wrong", null)
    }
}