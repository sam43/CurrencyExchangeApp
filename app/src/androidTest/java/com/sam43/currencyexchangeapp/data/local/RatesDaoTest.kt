package com.sam43.currencyexchangeapp.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sam43.currencyexchangeapp.TestDispatcherProvider
import com.sam43.currencyexchangeapp.data.db.AppDB
import com.sam43.currencyexchangeapp.data.local.dao.RateDao
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.CurrencyResponseDto
import com.sam43.currencyexchangeapp.dummyRatesAndroidTest
import com.sam43.currencyexchangeapp.utils.AppConstants
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class RatesDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDB
    private lateinit var dao: RateDao
    private val rates: Rates by lazy { dummyRatesAndroidTest() }
    // scope and dispatchers
    private val testDispatcher = TestDispatcherProvider()
    private val testScope = TestScope(testDispatcher.unconfined)
    private val networkContext: CoroutineContext = testDispatcher.unconfined

    @Before
    fun setupDB() {
        hiltRule.inject()
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDB::class.java).build()
        dao = database.rateDao
    }

    @Test
    fun insertExchangeRateItem_ReturnSuccess_If_matches_DB_item() = runTest {
        val exchangeItem = CurrencyResponseDto(base = AppConstants.DEFAULT_CURRENCY, rates = hashMapOf("USD" to "1", "BDT" to "183.83"), timestamp = 1655226000.0)
        dao.insertRateInfo(exchangeItem.toCurrencyInfoEntity())
        val items = dao.fetchRatesFromDB()?.toRateInfo()?.rates
        assertThat(items).isEqualTo(exchangeItem.toCurrencyInfoEntity().rates)
    }

    @Test(expected=NullPointerException::class)
    fun insertExchangeRateItem_ReturnFailed_If_insertNullRates_to_DB() = testScope.runTest {
        val exchangeItem = CurrencyResponseDto(base = AppConstants.DEFAULT_CURRENCY, rates = hashMapOf(), timestamp = 1655226000.0)
        dao.insertRateInfo(exchangeItem.toCurrencyInfoEntity())
        val items = dao.fetchRatesFromDB()?.toRateInfo()
        fail("Null pointer exception and the rate is NULL. result = $items")
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        database.close()
    }
}
