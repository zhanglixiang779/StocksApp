package com.example.stocksapp.fake

import com.example.stocksapp.data.models.Stock
import com.example.stocksapp.presentation.StocksViewModel
import com.example.stocksapp.presentation.composables.StocksButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * These unit tests use fake Repository for testing
 */
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FakeStocksViewModelTest {

    private val fakeStockRepository = FakeStockRepository()

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun assertTrue_WhenSuccessButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        viewModel.fetchStocks(StocksButton.SuccessButton)

        // Verify
        runTest {
            val expected = FakeStockRepository().getStocks().value.data?.stocks
            delay(100)
            val actual = viewModel.stocks.value.data?.stocks
            assertEquals(expected, actual)
        }
    }

    @Test
    fun assertTrue_WhenEmptyButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        viewModel.fetchStocks(StocksButton.EmptyButton)
        val expected = emptyList<Stock>()
        val actual = viewModel.stocks.value.data?.stocks

        // Verify
        assertEquals(expected, actual)
    }

    @Test
    fun assertTrue_WhenErrorButton_Clicked() {

        // Given
        val viewModel = StocksViewModel(fakeStockRepository)

        // when
        viewModel.fetchStocks(StocksButton.ErrorButton)
        runTest {
            val expected = FakeStockRepository().getErrorStocks().value.message
            delay(100)
            val actual = viewModel.stocks.value.message
            assertEquals(expected, actual)
        }
    }
}