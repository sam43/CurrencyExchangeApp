package com.sam43.currencyexchangeapp.usecases

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.dummyRatesTest
import com.sam43.currencyexchangeapp.mockedResponseForConversionRateList
import com.sam43.currencyexchangeapp.repository.IMainRepository
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetConvertedRatesTest {
    @MockK
    lateinit var repository: IMainRepository
    lateinit var usecase: GetConvertedRates
    private var amountForTest = "10.0"
    private var from = AppConstants.DEFAULT_CURRENCY
    private var to = "BDT"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.usecase = GetConvertedRates(repository = repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetch latest currency data from server`() = runTest {
        val expectedRateList: MutableList<CurrencyRateItem> = mockedResponseForConversionRateList(dummyRatesTest(), amountForTest, from)
        coEvery {
            repository.getConvertedRates(amountForTest, from, to)
        }.returns(flow {
            emit(Resource.Success(data = expectedRateList))
        })

        val data = usecase.invoke(amountForTest, from, to)
        val list = data.last().data
        println("fetch latest currency data from server: ${data.first()}")
        println("fetch latest list: ${expectedRateList[0].currency}")
        assertNotNull(data.last())
        assert(data.first() is Resource.Success)
        assert(data.last() is Resource.Success)
        assert(data.last() !is Resource.Error)
        assertEquals(expectedRateList, list)
        assertEquals(expectedRateList[0].currency, list?.get(0)?.currency)
        assertNotEquals(expectedRateList[1].currency, list?.get(0)?.currency)

        coVerify { usecase.invoke(amountForTest, from, to) }

        coVerify { repository.getConvertedRates(amountForTest, from, to) }

    }
}