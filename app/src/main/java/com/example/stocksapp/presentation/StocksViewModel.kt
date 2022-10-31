package com.example.stocksapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.domain.Repository
import com.example.stocksapp.presentation.composables.StocksButton
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.stocksapp.presentation.composables.StocksButton.SuccessButton
import com.example.stocksapp.presentation.composables.StocksButton.ErrorButton
import com.example.stocksapp.presentation.composables.StocksButton.EmptyButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _stocks: MutableStateFlow<NetworkResult<ApiStocks>>
        = MutableStateFlow(NetworkResult.Success(ApiStocks(emptyList())))

    val stocks: StateFlow<NetworkResult<ApiStocks>> = _stocks

    fun fetchStocks(button: StocksButton) {
        viewModelScope.launch {
            when(button) {
                EmptyButton -> {
                    repository.getEmptyStocks().collect { emptyStocks ->
                        _stocks.value = emptyStocks
                    }
                }
                ErrorButton -> {
                    repository.getErrorStocks().collect { errorStock ->
                        _stocks.value = errorStock
                    }
                }
                SuccessButton -> {
                    repository.getStocks().collect { stocks ->
                        _stocks.value = stocks
                    }
                }
            }
        }
    }
}