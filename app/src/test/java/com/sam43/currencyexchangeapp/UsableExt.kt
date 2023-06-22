package com.sam43.currencyexchangeapp

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.asMap
import com.sam43.currencyexchangeapp.utils.fetchRatesAsList
import com.sam43.currencyexchangeapp.utils.unitConvertedRate
import com.sam43.currencyexchangeapp.utils.to3decimalPoint
import io.mockk.every
import io.mockk.mockk
import org.jetbrains.annotations.TestOnly
import java.security.InvalidParameterException

fun dummyRatesTest() = Rates(FJD="2.21", MXN="17.1657", STD="22823.990504", SCR="13.373394", CDF="2380.0", BBD="2.0", GTQ="7.839187", CLP="802.55", HNL="24.72", UGX="3704.375216", ZAR="18.394109", TND="3.088", STN="22.8", CUC="1.0", BSD="1.0", SLL="17665.0", SDG="601.5", IQD="1310.0", CUP="25.75", GMD="59.5", TWD="30.946501", RSD="107.11", DOP="54.9", KMF="451.850104", MYR="4.646", FKP="0.785212", XOF="599.01624", GEL="2.6125", BTC="3.3464307E-5", UYU="37.942956", MAD="9.9875", CVE="101.375", TOP="2.3509", AZN="1.7", OMR="0.384962", PGK="3.525", KES="140.2", SEK="10.693953", CNH="7.181913", BTN="81.984391", UAH="36.928154", GNF="8650.0", ERN="15.0", MZN="63.87499", SVC="8.749485", ARS="252.08188", QAR="3.641", IRR="42275.0", XPD="7.3701E-4", CNY="7.1795", THB="34.866", UZS="11510.0", XPF="108.973075", MRU="34.58", BDT="108.182619", LYD="4.805", BMD="1.0", KWD="0.307289", PHP="55.606996", XPT="0.00105568", RUB="83.998335", PYG="7255.925349", ISK="135.05", JMD="154.082116", COP="4138.462848", MKD="56.447252", USD="1.0", DZD="135.63009", PAB="1.0", GGP="0.785212", SGD="1.342689", ETB="54.5", JEP="0.785212", KGS="87.3042", SOS="568.5", VUV="118.979", LAK="18637.5", BND="1.343834", XAF="599.01624", LRD="176.6", XAG="0.04397538", CHF="0.895109", HRK="6.880748", ALL="97.725", DJF="178.5", VES="27.220245", ZMW="17.403727", TZS="2395.0", VND="23510.050871", XAU="5.1756E-4", AUD="1.474861", ILS="3.62231", GHS="11.325", GYD="211.479226", KPW="900.0", BOB="6.909499", KHR="4126.0", MDL="17.9647", IDR="14951.240295", KYD="0.83328", AMD="386.16", BWP="13.268232", SHP="0.785212", TRY="23.562011", LBP="15079.276626", TJS="10.918998", JOD="0.71", AED="3.673075", HKD="7.828025", RWF="1152.0", EUR="0.913194", LSL="18.38", DKK="6.801477", CAD="1.3181", BGN="1.79015", MMK="2099.820837", MUR="45.499998", NOK="10.698605", SYP="2512.53", IMP="0.785212", ZWL="322.0", GIP="0.785212", RON="4.5318", LKR="307.990403", NGN="690.4", CRC="540.322494", CZK="21.673016", PKR="287.45", XCD="2.70255", ANG="1.802097", HTG="138.989888", BHD="0.376966", KZT="448.967081", SRD="37.6", SZL="18.230452", SAR="3.751424", TTD="6.785109", YER="250.349961", MVR="15.355", AFN="85.85558", INR="81.9824", AWG="1.8025", KRW="1292.959663", NPR="131.175149", JPY="142.0898", MNT="3519.0", AOA="758.5", PLN="4.04942", GBP="0.785212", SBD="8.323692", BYN="2.523834", HUF="337.193992", BIF="2825.216203", MWK="1024.5", MGA="4500.0", XDR="0.74573", BZD="2.015477", BAM="1.791108", EGP="30.9015", MOP="8.06418", NAD="18.38", SSP="130.26", NIO="36.545", PEN="3.6325", NZD="1.616259", WST="2.72551", TMT="3.5", CLF="0.029085", BRL="4.7648")
@TestOnly
@Throws(InvalidParameterException::class)
fun getConvertedRateAsObject(rates: Rates, amount: Double = 0.0, from: String, to: String): String =
    if (amount < 0.0) throw InvalidParameterException("Invalid value entered") else (amount * unitConvertedRate(rates, from, to)).toString()

fun mockedResponseForCurrencyResponse() = mockk<CurrencyResponse> {
    every { base } returns AppConstants.DEFAULT_CURRENCY
    every { rates } returns dummyRatesTest()
}
fun mockedResponseForConversionRates() = mockk<CurrencyRateItem> {
    every { currency } returns AppConstants.DEFAULT_CURRENCY
    every { value } returns (10.0 * unitConvertedRate(dummyRatesTest(), "USD", "JPY")).to3decimalPoint()
}
fun mockedResponseForConversionRateList(rates: Rates, amount: String = AppConstants.DEFAULT_VALUE, from: String)
    = fetchRatesAsList(rates.asMap(), rates, amount.toDouble(), from)