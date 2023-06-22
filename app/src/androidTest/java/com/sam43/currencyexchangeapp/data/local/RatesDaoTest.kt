package com.sam43.currencyexchangeapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sam43.currencyexchangeapp.TestDispatcherProvider
import com.sam43.currencyexchangeapp.data.db.AppDB
import com.sam43.currencyexchangeapp.data.db.AppDBTest
import com.sam43.currencyexchangeapp.data.local.dao.RateDao
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.dummyRatesAndroidTest
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.asMap
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class RatesDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: AppDBTest by lazy {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDBTest::class.java
        ).allowMainThreadQueries().build()
    }
    private lateinit var dao: RateDao
    private val rates: Rates by lazy { dummyRatesAndroidTest() }
    // scope and dispatchers
    private val testDispatcher = TestDispatcherProvider()
    private val testScope = TestScope(testDispatcher.unconfined)
    private val networkContext: CoroutineContext = testDispatcher.unconfined

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.rateDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun insertExchangeRateItem_ReturnSuccess_If_matches_DB_item() = testScope.runTest {
        val exchangeItem = CurrencyResponseDto(base = AppConstants.DEFAULT_CURRENCY, rates = rates.asMap() as HashMap<String, String>, timestamp = 1655226000.0)
        dao.insertRateInfos(exchangeItem.toCurrencyInfoEntity())
        val items = dao.getRatesOffline()?.rates
        assertThat(items).isEqualTo(exchangeItem.toCurrencyInfoEntity().rates)
    }

    @Test(expected=NullPointerException::class)
    fun insertExchangeRateItem_ReturnFailed_If_insertNullRates_to_DB() = testScope.runTest {
        val exchangeItem = CurrencyResponseDto(base = AppConstants.DEFAULT_CURRENCY, rates = hashMapOf(), timestamp = 1655226000.0)
        dao.insertRateInfos(exchangeItem.toCurrencyInfoEntity())
        val items = dao.getRatesOffline()?.rates
        fail("Null pointer exception and the rate is NULL. result = $items")
    }
}