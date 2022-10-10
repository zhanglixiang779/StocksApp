package com.example.stocksapp.presentation

import androidx.lifecycle.ViewModel
import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.domain.Repository
import com.example.stocksapp.presentation.composables.StocksButton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.stocksapp.presentation.composables.StocksButton.SuccessButton
import com.example.stocksapp.presentation.composables.StocksButton.ErrorButton
import com.example.stocksapp.presentation.composables.StocksButton.EmptyButton

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    lateinit var stocks: Flow<NetworkResult<ApiStocks>>

    fun fetchStocks(button: StocksButton): Flow<NetworkResult<ApiStocks>> {
        stocks = when(button) {
            EmptyButton -> {
                repository.getEmptyStocks()
            }
            ErrorButton -> {
                repository.getErrorStocks()
            }
            SuccessButton -> {
                repository.getStocks()
            }
        }

        return stocks
    }
}