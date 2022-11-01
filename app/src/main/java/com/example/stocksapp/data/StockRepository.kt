package com.example.stocksapp.data

import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.data.retrofit.safeApiCall
import com.example.stocksapp.domain.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
open class StockRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun getStocks(): NetworkResult<ApiStocks> {
        return safeApiCall { remoteDataSource.getStocks() }
    }

    override suspend fun getEmptyStocks(): NetworkResult<ApiStocks> {
        return safeApiCall { remoteDataSource.getEmptyStocks() }
    }

    override suspend fun getErrorStocks(): NetworkResult<ApiStocks> {
        return safeApiCall { remoteDataSource.getErrorStocks() }
    }
}