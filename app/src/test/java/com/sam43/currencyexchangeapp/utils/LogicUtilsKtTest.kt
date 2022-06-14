package com.sam43.currencyexchangeapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sam43.currencyexchangeapp.data.models.Rates
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LogicUtilsKtTest {

    @Mock
    private lateinit var rates: Rates

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `get rate from given base currency, return success if matches with BDT`() {
        val base = "BDT"
        val convertedRate = getRateForCurrency(base, rates)
        assertTrue(convertedRate == rates.bDT)
    }

    @Test
    fun `get rate from given base currency, return success if matches with USD`() {
        val base = "USD"
        val convertedRate = getRateForCurrency(base, rates)
        assertTrue(convertedRate == rates.uSD?.toDouble())
    }

    @Test
    fun `get rate from given base currency, return failed if base is null`() {
        val base: String? = null
        val convertedRate = getRateForCurrency(base.toString(), rates)
        assertFalse(convertedRate == rates.bDT)
        assertFalse(convertedRate == rates.uSD?.toDouble())
        assertFalse(convertedRate == rates.eUR)
    }

    @Test
    fun `get rate from given base currency, return failed if base is empty`() {
        val base = ""
        val convertedRate = getRateForCurrency(base, rates)
        assertFalse(convertedRate == rates.bDT)
        assertFalse(convertedRate == rates.uSD?.toDouble())
        assertFalse(convertedRate == rates.eUR)
    }

    @Test
    fun `get converted rate from give base to expecting currency, return success if matches`() {
        val from = "USD"
        val to = "BDT"
        val convertedRate = getConvertedRateAsObject(rates, 1.0, from, to).to3decimalPoint()
        println("Converted rate from $from to $to is: $convertedRate")
        assertTrue(convertedRate == getRateForCurrency(to, rates)?.to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if base is null`() {
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if base is empty`() {
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if expecting currency is null`() {
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if expecting currency is empty`() {
    }
}