package com.sam43.currencyexchangeapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var useCases: ConversionUseCases
    @Mock
    private lateinit var dispatcher: DispatcherProvider

    @Before
    fun setUp() {
        viewModel = MainViewModel(useCases, dispatcher)
    }

    @Test
    fun `base currency is not mandatory, return success`() = runBlocking(dispatcher.main) {
        viewModel.consumeRatesApi() // didn't pass the base currency as argument initially and takes "USD" by default
        val event = viewModel.conversion.value
        assertTrue(event == MainViewModel.CurrencyEvent.SuccessResponse(null))
    }
}