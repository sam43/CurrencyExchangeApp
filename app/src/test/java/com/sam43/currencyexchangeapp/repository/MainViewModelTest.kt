package com.sam43.currencyexchangeapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModel
import com.sam43.currencyexchangeapp.MainDispatcherRule
import com.sam43.currencyexchangeapp.TestDispatcherProvider
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.dummyRatesTest
import com.sam43.currencyexchangeapp.mockedResponseForConversionRateList
import com.sam43.currencyexchangeapp.usecases.ConversionUseCases
import com.sam43.currencyexchangeapp.utils.AppConstants
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest : ViewModel() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK lateinit var conversionUseCase: ConversionUseCases
    private lateinit var mainViewModel: MainViewModel
    private lateinit var dispatcherProvider: TestDispatcherProvider
    private lateinit var currencyResponse: CurrencyResponse

    private var amountForTest = "10.0"
    private var from = AppConstants.DEFAULT_CURRENCY
    private var to = "BDT"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dispatcherProvider = TestDispatcherProvider()
        mainViewModel = MainViewModel(useCases = conversionUseCase, dispatchers = dispatcherProvider)
        currencyResponse = mockk<CurrencyResponse>(relaxed = true)
    }

    @Test
    fun `test math`() {
        val sum = 3 + 5
        assert(sum == 2+6)
    }

    @Test
    fun `fetching rates from server`() = runTest {
        coEvery {
            conversionUseCase.getRates(any())
        } returns flow { dummyRatesTest() }

        mainViewModel.consumeRatesApi(AppConstants.DEFAULT_CURRENCY)
        launch {
            mainViewModel.conversion
                .onCompletion {t ->
                    assertEquals(MainViewModel.CurrencyEvent.Loading, mainViewModel.conversion.first())
                    assertEquals(MainViewModel.CurrencyEvent.SuccessResponse(currencyResponse), mainViewModel.conversion.last())
                    assertNotEquals(t, mainViewModel.conversion)
                }
        }
    }

    @Test
    fun `calculate conversion rate`() = runTest {
        val expectedList = mockedResponseForConversionRateList(dummyRatesTest(), amountForTest, from)
        coEvery {
            conversionUseCase.getConvertedRates.invoke(any(),any(),any())
        } returns flow { expectedList }
        mainViewModel.convert(amountForTest, from, to)
        launch { mainViewModel.conversionRates
            .onCompletion {t ->
                assertEquals(MainViewModel.CurrencyEvent.Loading, mainViewModel.conversionRates.first())
                assertEquals(MainViewModel.CurrencyEvent.SuccessListResponse(expectedList), mainViewModel.conversionRates.last())
                assertNotEquals(t, mainViewModel.conversionRates)
            }
        }
    }
}