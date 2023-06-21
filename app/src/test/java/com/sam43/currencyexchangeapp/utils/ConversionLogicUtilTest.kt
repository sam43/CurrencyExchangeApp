package com.sam43.currencyexchangeapp.utils


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.dummyRatesTest
import com.sam43.currencyexchangeapp.getConvertedRateAsObject
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.security.InvalidParameterException

@RunWith(MockitoJUnitRunner::class)
class ConversionLogicUtilTest {

    private lateinit var rates: Rates

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        rates = dummyRatesTest()
    }

    @Test
    fun `get rate from given base currency, return success if matches with BDT`() {
        val base = "BDT"
        val convertedRate = getRateForCurrency(base, rates)
        assertTrue(convertedRate == rates.BDT)
    }

    @Test
    fun `get rate from given base currency, return success if matches with USD`() {
        val base = "USD"
        val convertedRate = getRateForCurrency(base, rates)
        assertTrue(convertedRate == rates.USD)
    }

    @Test
    fun `get rate from given base currency, return failed if base is null`() {
        val base: String? = null
        val convertedRate = getRateForCurrency(base.toString(), rates)
        assertFalse(convertedRate == rates.BDT)
        assertFalse(convertedRate == rates.USD)
        assertFalse(convertedRate == rates.EUR)
    }

    @Test
    fun `get rate from given base currency, return failed if base is empty`() {
        val base = ""
        val convertedRate = getRateForCurrency(base, rates)
        assertFalse(convertedRate == rates.BDT)
        assertFalse(convertedRate == rates.USD)
        assertFalse(convertedRate == rates.EUR)
    }

    @Test
    fun `get converted rate from give base to expecting currency, return success if matches`() {
        val from = "USD"
        val to = "BDT"
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        assertTrue(convertedRate == getRateForCurrency(to, rates).to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if from value is null`() {
        val from = null
        val to = "BDT"
        val convertedRate = getConvertedRate(rates, from.toString(), to).to3decimalPoint()
        assertFalse(convertedRate == getRateForCurrency(to, rates).to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if 'from' value is empty`() {
        val from = ""
        val to = "BDT"
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        assertFalse(convertedRate == getRateForCurrency(to, rates).to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return 0 (default value) if 'to' value is null`() {
        val from = "USD"
        val to = null
        val convertedRate = getConvertedRate(rates, from, to.toString()).to3decimalPoint()
        assertTrue(convertedRate == getRateForCurrency(to.toString(), rates).to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return  0 (default value) if to value is empty`() {
        val from = "USD"
        val to = ""
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        println("Converted rate from $from to $to is: $convertedRate")
        println("Converted rate from ${getRateForCurrency(to, rates).to3decimalPoint()}")
        assertTrue(convertedRate == getRateForCurrency(to, rates).to3decimalPoint())
    }

    @Test
    fun `convert currencies using amount, return success`() {
        val from = "USD"
        val to = "BDT"
        val amount = 25.893
        val convertedRate = getConvertedRateAsObject(rates, amount, from, to)
        assertTrue(convertedRate == (amount * getRateForCurrency(to, rates).toDouble()).toString())
    }

    @Test(expected=InvalidParameterException::class)
    fun `convert currencies using amount, return failed if amount is -1`() {
        val expected = InvalidParameterException()
        val from = "USD"
        val to = "BDT"
        val convertedRate = getConvertedRateAsObject(rates, -1.0, from, to)
        fail("Getting invalid parameter exception for having '-1.0' amount in params, with final value = $convertedRate")
        assert(expected.message == "Invalid value entered")
    }

    @Test
    fun `convert currencies using amount, return failed if amount is not provided`() {
        val from = "USD"
        val to = "BDT"
        val convertedRate = getConvertedRateAsObject(rates = rates, from = from, to = to)
        // as the amount is not provided, so that we use the default value "1.0" as per calculation
        assertTrue(convertedRate == rates.BDT)
    }

    @Test
    fun `creation of currency list by default amount, return success if to is null then list is not null or empty`() =
        assertTrue(getRatesAsList(rates = rates, from = "USD").isNotEmpty())

    @Test
    fun `creation of currency list by provided amount, return success if list is not empty`() =
        assertTrue(getRatesAsList(rates = rates, amount = 25.9056, from = "USD").isNotEmpty())

    @Test
    fun `creation of currency list by provided amount but empty 'from' value provided, return success if list is empty`() =
        assertTrue(getRatesAsList(rates = rates, amount = 25.9056, "").isEmpty())
}