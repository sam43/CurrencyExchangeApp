package com.sam43.currencyexchangeapp.utils

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.Rates
import org.jetbrains.annotations.TestOnly


fun getRateForCurrency(base: String, rates: Rates): Double? = when (base) {
    "CAD" -> rates.cAD
    "HKD" -> rates.hKD
    "ISK" -> rates.iSK
    "EUR" -> rates.eUR
    "PHP" -> rates.pHP
    "DKK" -> rates.dKK
    "HUF" -> rates.hUF // problematic
    "CZK" -> rates.cZK
    "AUD" -> rates.aUD
    "RON" -> rates.rON
    "SEK" -> rates.sEK
    "BDT" -> rates.bDT
//        "BBD" -> rates.bBD?.toDouble()
//        "IDR" -> rates.iDR?.toDouble()
    "INR" -> rates.iNR
    "BRL" -> rates.bRL
    "RUB" -> rates.rUB
    "HRK" -> rates.hRK
    "JPY" -> rates.jPY
    "THB" -> rates.tHB
    "CHF" -> rates.cHF
    "SGD" -> rates.sGD
    "PLN" -> rates.pLN
    "BGN" -> rates.bGN
    "CNY" -> rates.cNY
    "NOK" -> rates.nOK
    "NZD" -> rates.nZD
    "ZAR" -> rates.zAR
    "USD" -> rates.uSD?.toDouble()
    "MXN" -> rates.mXN
    "ILS" -> rates.iLS
    "GBP" -> rates.gBP
    "KRW" -> rates.kRW
    "MYR" -> rates.mYR
    "AED" -> rates.aED
    "AFN" -> rates.aFN
    "ALL" -> rates.aLL
    "AMD" -> rates.aMD
    "ANG" -> rates.aNG
    "AOA" -> rates.aOA
    "ARS" -> rates.aRS
    "AWG" -> rates.aWG
    "AZN" -> rates.aZN
    "BAM" -> rates.bAM
    "BHD" -> rates.bHD
    "BIF" -> rates.bIF
//        "BMD" -> rates.bMD?.toDouble()
    "BND" -> rates.bND
    "BOB" -> rates.bOB
//        "BSD" -> rates.bSD?.toDouble()
    "BTC" -> rates.bTC
    "BTN" -> rates.bTN
    "BWP" -> rates.bWP
    "BZD" -> rates.bZD
    "CDF" -> rates.cDF
    "CLF" -> rates.cLF
    "CLP" -> rates.cLP
    "CNH" -> rates.cNH
    "COP" -> rates.cOP
    "CRC" -> rates.cRC
//        "CUC" -> rates.cUC?.toDouble()
    "CUP" -> rates.cUP
    "DZD" -> rates.dZD
    "ERN" -> rates.eRN
    "ETB" -> rates.eTB
    "FJD" -> rates.fJD
    "FKP" -> rates.fKP
    "GEL" -> rates.gEL
    "GHS" -> rates.gHS
    "GMD" -> rates.gMD
    "GNF" -> rates.gNF
    "GYD" -> rates.gYD
    "HNL" -> rates.hNL
    "HTG" -> rates.hTG
    "IMP" -> rates.iMP
    "IQD" -> rates.iQD
    "JEP" -> rates.jEP
    "JMD" -> rates.jMD
    "JOD" -> rates.jOD
    "KES" -> rates.kES
    "KGS" -> rates.kGS
    "KHR" -> rates.kHR
    "KMF" -> rates.kMF
//        "KPW" -> rates.kPW?.toDouble()
    "KWD" -> rates.kWD
    "KYD" -> rates.kYD
    "KZT" -> rates.jMD
    "LAK" -> rates.lAK
    "LBP" -> rates.lBP
    "LKR" -> rates.lKR
    "LRD" -> rates.lRD
    "LSL" -> rates.lSL
    "LYD" -> rates.lYD
    "MAD" -> rates.mAD
    "MDL" -> rates.mDL
    "MGA" -> rates.mGA
    "MKD" -> rates.mKD
    "MMK" -> rates.mMK
    "MNT" -> rates.mNT
    "MOP" -> rates.mOP
    "MRU" -> rates.mRU
    "MUR" -> rates.mUR
    "MVR" -> rates.mVR
    "MWK" -> rates.mWK
    "MZN" -> rates.mZN
    "NAD" -> rates.nAD
    "NGN" -> rates.nGN
    "NIO" -> rates.nIO
    "NPR" -> rates.nPR
    "OMR" -> rates.oMR
//        "PAB" -> rates.pAB?.toDouble()
    "PEN" -> rates.pEN
    "PGK" -> rates.pGK
    "PKR" -> rates.pKR
    "PYG" -> rates.pYG
    "QAR" -> rates.qAR
    "RSD" -> rates.rSD
    "RWF" -> rates.rWF
    "SAR" -> rates.sAR
//        "SBD" -> rates.pAB?.toDouble()
    "SCR" -> rates.sCR
    "SDG" -> rates.sDG
    "SHP" -> rates.sHP
    "SLL" -> rates.sLL
    "SOS" -> rates.sOS
    "SRD" -> rates.sRD
    "SSP" -> rates.sSP
    "STD" -> rates.sTD
    "STN" -> rates.sTN
    "SVC" -> rates.sVC
    "SYP" -> rates.sYP
    "SZL" -> rates.sZL
    "TJS" -> rates.tJS
    "TMT" -> rates.tMT
    "TND" -> rates.tND
    "TOP" -> rates.tOP
    "TRY" -> rates.tRY
    "TTD" -> rates.tTD
    "TWD" -> rates.tWD
//        "TZS" -> rates.tZS?.toDouble()
    "UAH" -> rates.uAH
    "UGX" -> rates.uGX
    "UYU" -> rates.uYU
    "UZS" -> rates.uZS
    "VES" -> rates.vES
//        "VND" -> rates.vND?.toDouble()
    "VUV" -> rates.vUV
    "WST" -> rates.wST
    "XAF" -> rates.xAF
    "XAG" -> rates.xAG
    "XAU" -> rates.xAU
    "XCD" -> rates.xCD
    "XDR" -> rates.xDR
    "XOF" -> rates.xOF
    "XPD" -> rates.xPD
    "XPF" -> rates.xPF
    "XPT" -> rates.xPT
    "YER" -> rates.yER
    "ZMW" -> rates.zMW
//        "ZWL" -> rates.zWL?.toDouble()
    else -> 0.0
}

fun getConvertedRate(rates: Rates, from: String, to: String): Double = (1.0/(getRateForCurrency(from, rates)!!)) * getRateForCurrency(to, rates)!!

@TestOnly
fun getConvertedRateAsObject(rates: Rates, amount: Double? = 1.0, from: String, to: String): Double =
    amount!! * getConvertedRate(rates, from, to)

fun getRatesAsList(rates: Rates, amount: Double? = 1.0, from: String): MutableList<CurrencyRateItem> {
    if (from.isEmpty()) return mutableListOf()
    return mutableListOf(
        CurrencyRateItem(
            country = "CAD",
            currency = (amount!! * getConvertedRate(rates, from, "CAD")).to3decimalPoint()
        ),
        CurrencyRateItem(country = "HKD", currency = (amount * getConvertedRate(rates, from, "HKD")).to3decimalPoint()),
        CurrencyRateItem(country = "BDT", currency = (amount * getConvertedRate(rates, from, "BDT")).to3decimalPoint()),
        CurrencyRateItem(country = "EUR", currency = (amount * getConvertedRate(rates, from, "EUR")).to3decimalPoint()),
        CurrencyRateItem(country = "KWD", currency = (amount * getConvertedRate(rates, from, "KWD")).to3decimalPoint()),
        CurrencyRateItem(country = "CNH", currency = (amount * getConvertedRate(rates, from, "CNH")).to3decimalPoint()),
        CurrencyRateItem(country = "USD", currency = (amount * getConvertedRate(rates, from, "USD")).to3decimalPoint()),
        CurrencyRateItem(country = "BTC", currency = (amount * getConvertedRate(rates, from, "BTC")).to3decimalPoint()),
        CurrencyRateItem(country = "GBP", currency = (amount * getConvertedRate(rates, from, "GBP")).to3decimalPoint()),
        CurrencyRateItem(country = "SGD", currency = (amount * getConvertedRate(rates, from, "SGD")).to3decimalPoint()),
        CurrencyRateItem(country = "NZD", currency = (amount * getConvertedRate(rates, from, "NZD")).to3decimalPoint()),
        CurrencyRateItem(country = "INR", currency = (amount * getConvertedRate(rates, from, "INR")).to3decimalPoint()),
        CurrencyRateItem(country = "CZK", currency = (amount * getConvertedRate(rates, from, "CZK")).to3decimalPoint()),
        CurrencyRateItem(country = "AUD", currency = (amount * getConvertedRate(rates, from, "AUD")).to3decimalPoint()),
        CurrencyRateItem(country = "RON", currency = (amount * getConvertedRate(rates, from, "RON")).to3decimalPoint()),
        CurrencyRateItem(country = "PHP", currency = (amount * getConvertedRate(rates, from, "PHP")).to3decimalPoint()),
        CurrencyRateItem(country = "ISK", currency = (amount * getConvertedRate(rates, from, "ISK")).to3decimalPoint()),
        CurrencyRateItem(country = "DKK", currency = (amount * getConvertedRate(rates, from, "DKK")).to3decimalPoint()),
        CurrencyRateItem(country = "JPY", currency = (amount * getConvertedRate(rates, from, "JPY")).to3decimalPoint()),
        CurrencyRateItem(country = "SEK", currency = (amount * getConvertedRate(rates, from, "SEK")).to3decimalPoint()),
        CurrencyRateItem(country = "XPF", currency = (amount * getConvertedRate(rates, from, "XPF")).to3decimalPoint()),
        CurrencyRateItem(country = "XPT", currency = (amount * getConvertedRate(rates, from, "XPT")).to3decimalPoint())
    )
}