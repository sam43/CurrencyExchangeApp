package com.sam43.currencyexchangeapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sam43.currencyexchangeapp.MainCoroutineRule
import com.sam43.currencyexchangeapp.data.remote.RatesDto
import com.sam43.currencyexchangeapp.dummyRatesTestDto
import com.sam43.currencyexchangeapp.network.Timer
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.usecases.GetConvertedRates
import com.sam43.currencyexchangeapp.usecases.GetRates
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var rates: RatesDto
    @Mock
    private lateinit var repository: MainRepository

    @Mock
    private lateinit var timer: Timer
    private lateinit var useCases: ConversionUseCases
    @Mock
    private lateinit var dispatcher: DispatcherProvider

    @Before
    fun setUp() {
        useCases = ConversionUseCases(GetRates(repository, timer), GetConvertedRates(repository, timer))
        viewModel = MainViewModel(useCases, dispatcher)
        rates = dummyRatesTestDto()
    }

    @Test(expected=NullPointerException::class)
    fun `base currency is mandatory for repository, return failed if passed empty value`() = runBlocking(dispatcher.main) {
        //viewModel.consumeRatesApi() // didn't pass the base currency as argument initially and takes "USD" by default
        val firstItem = useCases.getRates("").first()
        println("firstItem: $firstItem")
        fail("Getting Null pointer exception as base value is empty. result: $firstItem")
    }

    @Test(expected=NullPointerException::class)
    fun `base currency is mandatory for repository, return success if passed non-empty value`() = runBlocking(dispatcher.main) {
        val firstItem = useCases.getRates("USD").first().data?.rates?.uSD
        println("EmitTest: firstItem: $firstItem")
        assertFalse(firstItem == rates.uSD)
    }
}