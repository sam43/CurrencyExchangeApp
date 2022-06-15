package com.sam43.currencyexchangeapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.data.remote.RatesDto
import com.sam43.currencyexchangeapp.dummyRatesAndroidTest
import com.sam43.currencyexchangeapp.dummyRatesAndroidTestDto
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RatesDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: AppDB
    private lateinit var dao: RateDao
    private lateinit var rates: RatesDto
    // scope and dispatchers
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)
    private val networkContext: CoroutineContext = testDispatcher

    @Before
    fun setup() {
        rates = dummyRatesAndroidTestDto()
        hiltRule.inject()
        dao = database.rateDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertExchangeRateItem_ReturnSuccess_If_matches_DB_item() = testScope.runBlockingTest {
        val exchangeItem = CurrencyResponseDto(base = "USD", rates = rates, timestamp = 1655226000)
        dao.insertRateInfos(exchangeItem.toCurrencyInfoEntity())
        val items = dao.getRatesOffline()?.rates
        assertThat(items).isEqualTo(exchangeItem.toCurrencyInfoEntity().rates)
    }

    @Test(expected=NullPointerException::class)
    fun insertExchangeRateItem_ReturnFailed_If_insertNullRates_to_DB() = testScope.runBlockingTest {
        val exchangeItem = CurrencyResponseDto(base = "USD", rates = null, timestamp = 1655226000)
        dao.insertRateInfos(exchangeItem.toCurrencyInfoEntity())
        val items = dao.getRatesOffline()?.rates
        fail("Null pointer exception and the rate is NULL. result = $items")
    }
}