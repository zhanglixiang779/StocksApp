package com.example.stocksapp.mock

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.StockRepository
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.data.models.Stock
import com.example.stocksapp.presentation.StocksViewModel
import com.example.stocksapp.presentation.composables.StocksButton
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * These unit tests use Mock Repository for testing
 */
@RunWith(MockitoJUnitRunner::class)
class MockStocksViewModelTest {

    private val mockStockRepository = mock(StockRepository::class.java)

    @Test
    fun `when success button clicked it should get stocks`() {

        // Given
        val viewModel = StocksViewModel(mockStockRepository)

        val stocks = listOf(
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

        `when`(mockStockRepository.getStocks()).thenReturn(
            flow {
                emit(NetworkResult.Success(ApiStocks(stocks)))
            }
        )

        // when
        viewModel.fetchStocks(StocksButton.SuccessButton)

        // Verify
        verify(mockStockRepository).getStocks()
    }

    @Test
    fun `when empty button clicked it should get empty stocks`() {

        // Given
        val emptyStock = emptyList<Stock>()
        val viewModel = StocksViewModel(mockStockRepository)

        `when`(mockStockRepository.getEmptyStocks()).thenReturn(
            flow {
                emit(NetworkResult.Success(ApiStocks(emptyStock)))
            }
        )

        // when
        viewModel.fetchStocks(StocksButton.EmptyButton)

        // Verify
        verify(mockStockRepository).getEmptyStocks()
    }

    @Test
    fun `when error button clicked it should get error stocks`() {

        // Given
        val viewModel = StocksViewModel(mockStockRepository)

        `when`(mockStockRepository.getErrorStocks()).thenReturn(
            flow {
                emit(NetworkResult.Error("something went wrong"))
            }
        )

        // when
        viewModel.fetchStocks(StocksButton.ErrorButton)

        // Verify
        verify(mockStockRepository).getErrorStocks()
    }
}