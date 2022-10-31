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
    private val remoteDataSource: RemoteDataSource,
    private val externalScope: CoroutineScope
) : Repository {

    override suspend fun getStocks(): StateFlow<NetworkResult<ApiStocks>> {
        return request { remoteDataSource.getStocks() }
    }

    override suspend fun getEmptyStocks(): StateFlow<NetworkResult<ApiStocks>> {
        return request { remoteDataSource.getEmptyStocks() }
    }

    override suspend fun getErrorStocks(): StateFlow<NetworkResult<ApiStocks>> {
        return request { remoteDataSource.getErrorStocks() }
    }

    private fun request(apiCall: suspend () -> Response<ApiStocks>): StateFlow<NetworkResult<ApiStocks>> {
        return flow {
            emit(safeApiCall { apiCall() })
        }.flowOn(Dispatchers.IO)
            .stateIn(
                scope = externalScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = NetworkResult.Loading()
            )
    }
}