package com.example.stocksapp.fake

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.data.models.Stock
import com.example.stocksapp.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStockRepository : Repository {

    override fun getStocks(): Flow<NetworkResult<ApiStocks>> {
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
        return flow {
            emit(NetworkResult.Success(ApiStocks(fakeStocks)))
        }
    }

    override fun getEmptyStocks(): Flow<NetworkResult<ApiStocks>> {
        val emptyStock = emptyList<Stock>()
        return flow {
            emit(NetworkResult.Success(ApiStocks(emptyStock)))
        }
    }

    override fun getErrorStocks(): Flow<NetworkResult<ApiStocks>> {
        return flow {
            emit(NetworkResult.Error("something went wrong", null))
        }
    }
}