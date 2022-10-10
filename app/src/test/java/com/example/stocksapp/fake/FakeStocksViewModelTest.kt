package com.example.stocksapp.fake

import com.example.stocksapp.presentation.StocksViewModel
import com.example.stocksapp.presentation.composables.StocksButton
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * These unit tests use fake Repository for testing
 */
@RunWith(MockitoJUnitRunner::class)
class FakeStocksViewModelTest {

    private val fakeStockRepository = FakeStockRepository()

    @Test
    fun assertTrue_WhenSuccessButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        val expected = viewModel.fetchStocks(StocksButton.SuccessButton)
        val actual = viewModel.stocks

        // Verify
        assertEquals(expected, actual)
    }

    @Test
    fun assertTrue_WhenEmptyButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        val expected = viewModel.fetchStocks(StocksButton.EmptyButton)
        val actual = viewModel.stocks

        // Verify
        assertEquals(expected, actual)
    }

    @Test
    fun assertTrue_WhenErrorButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        val expected = viewModel.fetchStocks(StocksButton.ErrorButton)
        val actual = viewModel.stocks

        // Verify
        assertEquals(expected, actual)
    }
}