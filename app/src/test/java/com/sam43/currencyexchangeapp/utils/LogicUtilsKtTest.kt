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

    private lateinit var rates: Rates

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        rates = Rates(
            uSD = 1,
            bDT = 94.317,
            cAD = 1.296,
            eUR = 0.96,
            gBP = 0.835


        )
    }

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
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        assertTrue(convertedRate == getRateForCurrency(to, rates)?.to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if from value is null`() {
        val from = null
        val to = "BDT"
        val convertedRate = getConvertedRate(rates, from.toString(), to).to3decimalPoint()
        assertFalse(convertedRate == getRateForCurrency(to, rates)?.to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return failed if from value is empty`() {
        val from = ""
        val to = "BDT"
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        assertFalse(convertedRate == getRateForCurrency(to, rates)?.to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return 0 (default value) if to value is null`() {
        val from = "USD"
        val to = null
        val convertedRate = getConvertedRate(rates, from, to.toString()).to3decimalPoint()
        println("Converted rate from $from to $to is: $convertedRate")
        println("Converted rate from ${getRateForCurrency(to.toString(), rates)?.to3decimalPoint()}")
        assertTrue(convertedRate == getRateForCurrency(to.toString(), rates)?.to3decimalPoint())
    }

    @Test
    fun `get converted rate from give base to expecting currency, return  0 (default value) if to value is empty`() {
        val from = "USD"
        val to = ""
        val convertedRate = getConvertedRate(rates, from, to).to3decimalPoint()
        println("Converted rate from $from to $to is: $convertedRate")
        println("Converted rate from ${getRateForCurrency(to, rates)?.to3decimalPoint()}")
        assertTrue(convertedRate == getRateForCurrency(to, rates)?.to3decimalPoint())
    }

    @Test
    fun `convert currencies using amount, return success`() {
        val from = "USD"
        val to = "BDT"
        val amount = 25.893
        val convertedRate = getConvertedRateAsObject(rates, amount, from, to)
        println("Converted rate from $from to $to is: $convertedRate")
        println("Converted amount calculated rate from ${amount * getRateForCurrency(to, rates)!!}")
        assertTrue(convertedRate == (amount * getRateForCurrency(to, rates)!!))
    }
}