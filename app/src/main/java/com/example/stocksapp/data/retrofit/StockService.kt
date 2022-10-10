package com.example.stocksapp.data.retrofit

import com.example.stocksapp.constants.EMPTY_API_ENDPOINT
import com.example.stocksapp.constants.ERROR_API_ENDPOINT
import com.example.stocksapp.constants.SUCCESS_API_ENDPOINT
import com.example.stocksapp.data.models.ApiStocks
import retrofit2.Response
import retrofit2.http.GET

interface StockService {
    @GET(SUCCESS_API_ENDPOINT)
    suspend fun getStocks(): Response<ApiStocks>

    @GET(EMPTY_API_ENDPOINT)
    suspend fun getEmptyStocks(): Response<ApiStocks>

    @GET(ERROR_API_ENDPOINT)
    suspend fun getErrorStocks(): Response<ApiStocks>
}