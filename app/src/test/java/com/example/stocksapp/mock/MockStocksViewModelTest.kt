package com.example.stocksapp.mock

import com.example.stocksapp.data.NetworkResult
import com.example.stocksapp.data.StockRepository
import com.example.stocksapp.data.models.ApiStocks
import com.example.stocksapp.data.models.Stock
import com.example.stocksapp.fake.FakeStockRepository
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
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * These unit tests use Mock Repository for testing
 */
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MockStocksViewModelTest {

    private val mockStockRepository = mock(StockRepository::class.java)

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
    fun `when success button clicked it should get stocks`() {
        runTest {
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
                NetworkResult.Success(ApiStocks(stocks))
            )

            // when
            viewModel.fetchStocks(StocksButton.SuccessButton)

            // Verify
            val expected = mockStockRepository.getStocks().data?.stocks
            delay(100)
            val actual = viewModel.stocks.value.data?.stocks
            Assert.assertEquals(expected, actual)
        }


    }

    @Test
    fun `when empty button clicked it should get empty stocks`() {
        runTest {
            // Given
            val emptyStock = emptyList<Stock>()
            val viewModel = StocksViewModel(mockStockRepository)

            `when`(mockStockRepository.getEmptyStocks()).thenReturn(
                NetworkResult.Success(ApiStocks(emptyStock))
            )

            // when
            viewModel.fetchStocks(StocksButton.EmptyButton)

            val expected = emptyList<Stock>()
            delay(100)
            val actual = viewModel.stocks.value.data?.stocks

            // Verify
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun `when error button clicked it should get error stocks`() {
        runTest {
            // Given
            val viewModel = StocksViewModel(mockStockRepository)
            `when`(mockStockRepository.getErrorStocks()).thenReturn(
                NetworkResult.Error("something went wrong", null)
            )

            // when
            viewModel.fetchStocks(StocksButton.ErrorButton)

            val expected = FakeStockRepository().getErrorStocks().message
            delay(100)
            val actual = viewModel.stocks.value.message

            // Verify
            Assert.assertEquals(expected, actual)
        }
    }
}