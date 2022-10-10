package com.example.stocksapp.domain

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import kotlinx.coroutines.flow.Flow

interface Repository {

    /**
     * Calling Success api endpoint
     */
    fun getStocks(): Flow<NetworkResult<ApiStocks>>

    /**
     * Calling Empty stocks api endpoint
     */
    fun getEmptyStocks(): Flow<NetworkResult<ApiStocks>>

    /**
     * Calling Malformed api endpoint
     */
    fun getErrorStocks(): Flow<NetworkResult<ApiStocks>>
}