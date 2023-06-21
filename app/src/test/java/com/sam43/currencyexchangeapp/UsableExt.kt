package com.sam43.currencyexchangeapp

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.RatesDto
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.getConvertedRate
import com.sam43.currencyexchangeapp.utils.to3decimalPoint
import io.mockk.every
import io.mockk.mockk
import org.jetbrains.annotations.TestOnly
import java.security.InvalidParameterException

fun dummyRatesTest() = Rates(FJD="2.21", MXN="17.1657", STD="22823.990504", SCR="13.373394", CDF="2380.0", BBD="2.0", GTQ="7.839187", CLP="802.55", HNL="24.72", UGX="3704.375216", ZAR="18.394109", TND="3.088", STN="22.8", CUC="1.0", BSD="1.0", SLL="17665.0", SDG="601.5", IQD="1310.0", CUP="25.75", GMD="59.5", TWD="30.946501", RSD="107.11", DOP="54.9", KMF="451.850104", MYR="4.646", FKP="0.785212", XOF="599.01624", GEL="2.6125", BTC="3.3464307E-5", UYU="37.942956", MAD="9.9875", CVE="101.375", TOP="2.3509", AZN="1.7", OMR="0.384962", PGK="3.525", KES="140.2", SEK="10.693953", CNH="7.181913", BTN="81.984391", UAH="36.928154", GNF="8650.0", ERN="15.0", MZN="63.87499", SVC="8.749485", ARS="252.08188", QAR="3.641", IRR="42275.0", XPD="7.3701E-4", CNY="7.1795", THB="34.866", UZS="11510.0", XPF="108.973075", MRU="34.58", BDT="108.182619", LYD="4.805", BMD="1.0", KWD="0.307289", PHP="55.606996", XPT="0.00105568", RUB="83.998335", PYG="7255.925349", ISK="135.05", JMD="154.082116", COP="4138.462848", MKD="56.447252", USD="1.0", DZD="135.63009", PAB="1.0", GGP="0.785212", SGD="1.342689", ETB="54.5", JEP="0.785212", KGS="87.3042", SOS="568.5", VUV="118.979", LAK="18637.5", BND="1.343834", XAF="599.01624", LRD="176.6", XAG="0.04397538", CHF="0.895109", HRK="6.880748", ALL="97.725", DJF="178.5", VES="27.220245", ZMW="17.403727", TZS="2395.0", VND="23510.050871", XAU="5.1756E-4", AUD="1.474861", ILS="3.62231", GHS="11.325", GYD="211.479226", KPW="900.0", BOB="6.909499", KHR="4126.0", MDL="17.9647", IDR="14951.240295", KYD="0.83328", AMD="386.16", BWP="13.268232", SHP="0.785212", TRY="23.562011", LBP="15079.276626", TJS="10.918998", JOD="0.71", AED="3.673075", HKD="7.828025", RWF="1152.0", EUR="0.913194", LSL="18.38", DKK="6.801477", CAD="1.3181", BGN="1.79015", MMK="2099.820837", MUR="45.499998", NOK="10.698605", SYP="2512.53", IMP="0.785212", ZWL="322.0", GIP="0.785212", RON="4.5318", LKR="307.990403", NGN="690.4", CRC="540.322494", CZK="21.673016", PKR="287.45", XCD="2.70255", ANG="1.802097", HTG="138.989888", BHD="0.376966", KZT="448.967081", SRD="37.6", SZL="18.230452", SAR="3.751424", TTD="6.785109", YER="250.349961", MVR="15.355", AFN="85.85558", INR="81.9824", AWG="1.8025", KRW="1292.959663", NPR="131.175149", JPY="142.0898", MNT="3519.0", AOA="758.5", PLN="4.04942", GBP="0.785212", SBD="8.323692", BYN="2.523834", HUF="337.193992", BIF="2825.216203", MWK="1024.5", MGA="4500.0", XDR="0.74573", BZD="2.015477", BAM="1.791108", EGP="30.9015", MOP="8.06418", NAD="18.38", SSP="130.26", NIO="36.545", PEN="3.6325", NZD="1.616259", WST="2.72551", TMT="3.5", CLF="0.029085", BRL="4.7648")

fun dummyRatesTestDto() = RatesDto(AED=3.6731, AFN=88.999995, ALL=114.902228, AMD=422.327295, ANG=1.801893, AOA=434.36325, ARS=122.467392, AUD=1.455625, AWG=1.8005, AZN=1.7, BAM=1.873957, BDT=94.317169, BGN=1.876368, BHD=0.376973, BIF=2032.0, BMD=1.0, BND=1.391518, BOB=6.883719, BRL=5.1397, BTC=4.4482222E-5, BTN=78.036557, BWP=12.298022, BYN=3.373895, BZD=2.015388, CAD=1.295284, CDF=2005.0, CHF=1.002636, CLF=0.031475, CLP=867.1, CNH=6.75701, CNY=6.741, COP=3971.17, CRC=682.916314, CUP=25.75, CVE=106.225, CZK=23.7472, DJF=177.996866, DKK=7.13869, DOP=55.05, DZD=146.527208, EGP=18.741, ERN=15.000001, ETB=52.029953, EUR=0.959674, FJD=2.2032, FKP=0.833743, GBP=0.833743, GEL=2.94, GGP=0.833743, GHS=7.948585, GIP=0.833743, GMD=54.15, GNF=8850.0, GTQ=7.728518, GYD=209.173243, HKD=7.85005, HNL=24.565002, HRK=7.2196, HTG=115.011216, HUF=385.826435, ILS=3.473825, IMP=0.833743, INR=78.014803, IQD=1459.249316, ISK=132.15, JEP=0.833743, JMD=152.906066, JOD=0.709, JPY=134.98216667, KES=117.2, KGS=79.500507, KHR=4061.2004, KMF=471.620221, KRW=1291.226523, KWD=0.307497, KYD=0.833206, KZT=437.370261, LAK=14702.201674, LBP=1510.17822, LKR=360.104799, LRD=151.999978, LSL=16.075713, LYD=4.823988, MAD=10.046086, MDL=19.098633, MGA=4060.761082, MKD=58.726373, MMK=1851.151781, MNT=3124.087599, MOP=8.083895, MRU=36.712978, MUR=44.400304, MVR=15.36, MWK=1021.413253, MXN=20.62429, MYR=4.4225, MZN=63.900001, NAD=16.09, NGN=419.672884, NIO=35.843706, NOK=10.022737, NPR=124.858187, NZD=1.610617, OMR=0.384994, PEN=3.778457, PGK=3.568239, PHP=53.279003, PKR=206.211559, PLN=4.472482, PYG=6873.03188, QAR=3.671157, RON=4.7438, RSD=112.63, RUB=58.750005, RWF=1021.21146, SAR=3.751709, SBD=8.10712, SCR=12.844943, SDG=456.5, SEK=10.212826, SGD=1.393346, SHP=0.833743, SLL=13130.0, SOS=578.433674, SRD=21.85, SSP=130.26, STD=23263.0, STN=23.7, SVC=8.748934, SYP=2512.53, SZL=16.075809, THB=35.035292, TJS=10.898081, TMT=3.51, TND=3.0855, TOP=2.344563, TRY=17.279, TTD=6.786371, TWD=29.694002, UAH=29.538455, UGX=3754.368852, USD=1.0, UYU=40.054231, UZS=10973.257095, VES=5.30395, VUV=116.538833, WST=2.661237, XAF=629.504767, XAG=0.04777146, XAU=5.5173E-4, XCD=2.70255, XDR=0.730762, XOF=629.504767, XPD=5.5312E-4, XPF=114.51955, XPT=0.00108344, YER=250.299955, ZAR=16.059074, ZMW=16.897008)

@TestOnly
@Throws(InvalidParameterException::class)
fun getConvertedRateAsObject(rates: Rates, amount: Double = 0.0, from: String, to: String): String =
    if (amount < 0.0) throw InvalidParameterException("Invalid value entered") else (amount * getConvertedRate(rates, from, to)).toString()

fun mockedResponseForCurrencyResponse() = mockk<CurrencyResponse> {
    every { base } returns AppConstants.DEFAULT_CURRENCY
    every { rates } returns dummyRatesTest()
}
fun mockedResponseForConversionRates() = mockk<CurrencyRateItem> {
    every { currency } returns AppConstants.DEFAULT_CURRENCY
    every { value } returns (10.0 * getConvertedRate(dummyRatesTest(), "USD", "JPY")).to3decimalPoint()
}
fun mockedResponseForConversionRateList(rates: Rates, amount: String = AppConstants.DEFAULT_VALUE, from: String) = mutableListOf(
    CurrencyRateItem(
        currency = "CAD",
        value = (amount.toDouble() * getConvertedRate(rates, from, "CAD")).to3decimalPoint()
    ),
    CurrencyRateItem(currency = "BDT", value = (amount.toDouble() * getConvertedRate(rates, from, "BDT")).to3decimalPoint()),
    CurrencyRateItem(currency = "EUR", value = (amount.toDouble() * getConvertedRate(rates, from, "EUR")).to3decimalPoint()),
    CurrencyRateItem(currency = "KWD", value = (amount.toDouble() * getConvertedRate(rates, from, "KWD")).to3decimalPoint()),
    CurrencyRateItem(currency = "JPY", value = (amount.toDouble() * getConvertedRate(rates, from, "JPY")).to3decimalPoint())
)