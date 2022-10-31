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

    override suspend fun getStocks(): StateFlow<NetworkResult<ApiStocks>> {
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
        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = CoroutineScope(Job()),
                started = SharingStarted.WhileSubscribed(),
                initialValue = NetworkResult.Loading()
            )
    }

    override suspend fun getEmptyStocks(): StateFlow<NetworkResult<ApiStocks>> {
        val emptyStock = emptyList<Stock>()
        return flow {
            emit(NetworkResult.Success(ApiStocks(emptyStock)))
        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = CoroutineScope(Job()),
                started = SharingStarted.WhileSubscribed(),
                initialValue = NetworkResult.Loading()
            )
    }

    override suspend fun getErrorStocks(): StateFlow<NetworkResult<ApiStocks>> {
        return flow {
            emit(NetworkResult.Error("something went wrong", null))
        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = CoroutineScope(Job()),
                started = SharingStarted.WhileSubscribed(),
                initialValue = NetworkResult.Loading()
            )
    }
}