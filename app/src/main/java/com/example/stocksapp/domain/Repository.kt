package com.example.stocksapp.domain

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import kotlinx.coroutines.flow.StateFlow

interface Repository {

    /**
     * Calling Success api endpoint
     */
    suspend fun getStocks(): StateFlow<NetworkResult<ApiStocks>>

    /**
     * Calling Empty stocks api endpoint
     */
    suspend fun getEmptyStocks(): StateFlow<NetworkResult<ApiStocks>>

    /**
     * Calling Malformed api endpoint
     */
    suspend fun getErrorStocks(): StateFlow<NetworkResult<ApiStocks>>
}