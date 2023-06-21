package com.sam43.currencyexchangeapp.utils

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.Rates
import org.jetbrains.annotations.TestOnly
import java.security.InvalidParameterException
import kotlin.reflect.full.memberProperties
import kotlin.time.times

fun getRateForCurrency(base: String, rates: Rates): String = when (base) {
    "CAD" -> rates.CAD
    "HKD" -> rates.HKD
    "ISK" -> rates.ISK
    "EUR" -> rates.EUR
    "PHP" -> rates.PHP
    "DKK" -> rates.DKK
    "HUF" -> rates.HUF // PROBLEMATIC
    "CZK" -> rates.CZK
    "AUD" -> rates.AUD
    "RON" -> rates.RON
    "SEK" -> rates.SEK
    "BDT" -> rates.BDT
    "BBD" -> rates.BBD
    "IDR" -> rates.IDR
    "INR" -> rates.INR
    "BRL" -> rates.BRL
    "RUB" -> rates.RUB
    "HRK" -> rates.HRK
    "JPY" -> rates.JPY
    "THB" -> rates.THB
    "CHF" -> rates.CHF
    "SGD" -> rates.SGD
    "PLN" -> rates.PLN
    "BGN" -> rates.BGN
    "CNY" -> rates.CNY
    "NOK" -> rates.NOK
    "NZD" -> rates.NZD
    "ZAR" -> rates.ZAR
    "USD" -> rates.USD
    "MXN" -> rates.MXN
    "ILS" -> rates.ILS
    "GBP" -> rates.GBP
    "KRW" -> rates.KRW
    "MYR" -> rates.MYR
    "AED" -> rates.AED
    "AFN" -> rates.AFN
    "ALL" -> rates.ALL
    "AMD" -> rates.AMD
    "ANG" -> rates.ANG
    "AOA" -> rates.AOA
    "ARS" -> rates.ARS
    "AWG" -> rates.AWG
    "AZN" -> rates.AZN
    "BAM" -> rates.BAM
    "BHD" -> rates.BHD
    "BIF" -> rates.BIF
    "BMD" -> rates.BMD
    "BND" -> rates.BND
    "BOB" -> rates.BOB
    "BSD" -> rates.BSD
    "BTC" -> rates.BTC
    "BTN" -> rates.BTN
    "BWP" -> rates.BWP
    "BZD" -> rates.BZD
    "CDF" -> rates.CDF
    "CLF" -> rates.CLF
    "CLP" -> rates.CLP
    "CNH" -> rates.CNH
    "COP" -> rates.COP
    "CRC" -> rates.CRC
    "CUC" -> rates.CUC
    "CUP" -> rates.CUP
    "DZD" -> rates.DZD
    "ERN" -> rates.ERN
    "ETB" -> rates.ETB
    "FJD" -> rates.FJD
    "FKP" -> rates.FKP
    "GEL" -> rates.GEL
    "GHS" -> rates.GHS
    "GMD" -> rates.GMD
    "GNF" -> rates.GNF
    "GYD" -> rates.GYD
    "HNL" -> rates.HNL
    "HTG" -> rates.HTG
    "IMP" -> rates.IMP
    "IQD" -> rates.IQD
    "JEP" -> rates.JEP
    "JMD" -> rates.JMD
    "JOD" -> rates.JOD
    "KES" -> rates.KES
    "KGS" -> rates.KGS
    "KHR" -> rates.KHR
    "KMF" -> rates.KMF
    "KPW" -> rates.KPW
    "KWD" -> rates.KWD
    "KYD" -> rates.KYD
    "KZT" -> rates.JMD
    "LAK" -> rates.LAK
    "LBP" -> rates.LBP
    "LKR" -> rates.LKR
    "LRD" -> rates.LRD
    "LSL" -> rates.LSL
    "LYD" -> rates.LYD
    "MAD" -> rates.MAD
    "MDL" -> rates.MDL
    "MGA" -> rates.MGA
    "MKD" -> rates.MKD
    "MMK" -> rates.MMK
    "MNT" -> rates.MNT
    "MOP" -> rates.MOP
    "MRU" -> rates.MRU
    "MUR" -> rates.MUR
    "MVR" -> rates.MVR
    "MWK" -> rates.MWK
    "MZN" -> rates.MZN
    "NAD" -> rates.NAD
    "NGN" -> rates.NGN
    "NIO" -> rates.NIO
    "NPR" -> rates.NPR
    "OMR" -> rates.OMR
    "PAB" -> rates.PAB
    "PEN" -> rates.PEN
    "PGK" -> rates.PGK
    "PKR" -> rates.PKR
    "PYG" -> rates.PYG
    "QAR" -> rates.QAR
    "RSD" -> rates.RSD
    "RWF" -> rates.RWF
    "SAR" -> rates.SAR
    "SBD" -> rates.PAB
    "SCR" -> rates.SCR
    "SDG" -> rates.SDG
    "SHP" -> rates.SHP
    "SLL" -> rates.SLL
    "SOS" -> rates.SOS
    "SRD" -> rates.SRD
    "SSP" -> rates.SSP
    "STD" -> rates.STD
    "STN" -> rates.STN
    "SVC" -> rates.SVC
    "SYP" -> rates.SYP
    "SZL" -> rates.SZL
    "TJS" -> rates.TJS
    "TMT" -> rates.TMT
    "TND" -> rates.TND
    "TOP" -> rates.TOP
    "TRY" -> rates.TRY
    "TTD" -> rates.TTD
    "TWD" -> rates.TWD
    "TZS" -> rates.TZS
    "UAH" -> rates.UAH
    "UGX" -> rates.UGX
    "UYU" -> rates.UYU
    "UZS" -> rates.UZS
    "VES" -> rates.VES
    "VND" -> rates.VND
    "VUV" -> rates.VUV
    "WST" -> rates.WST
    "XAF" -> rates.XAF
    "XAG" -> rates.XAG
    "XAU" -> rates.XAU
    "XCD" -> rates.XCD
    "XDR" -> rates.XDR
    "XOF" -> rates.XOF
    "XPD" -> rates.XPD
    "XPF" -> rates.XPF
    "XPT" -> rates.XPT
    "YER" -> rates.YER
    "ZMW" -> rates.ZMW
    "ZWL" -> rates.ZWL
    else -> "0.0"
}

fun getConvertedRate(rates: Rates, from: String, to: String): Double = (1.0/(getRateForCurrency(from, rates)).toDouble()) * getRateForCurrency(to, rates).toDouble()

fun getRatesAsList(rates: Rates, amount: Double = 1.0, from: String): MutableList<CurrencyRateItem> {
    if (from.isEmpty()) return mutableListOf()
    return mutableListOf(
        CurrencyRateItem(
            currency = "CAD",
            value = (amount * getConvertedRate(rates, from, "CAD")).to3decimalPoint()
        ),
        CurrencyRateItem(currency = "BDT", value = (amount * getConvertedRate(rates, from, "BDT")).to3decimalPoint()),
        CurrencyRateItem(currency = "EUR", value = (amount * getConvertedRate(rates, from, "EUR")).to3decimalPoint()),
        CurrencyRateItem(currency = "KWD", value = (amount * getConvertedRate(rates, from, "KWD")).to3decimalPoint()),
        CurrencyRateItem(currency = "JPY", value = (amount * getConvertedRate(rates, from, "JPY")).to3decimalPoint()),
        CurrencyRateItem(currency = "CNH", value = (amount * getConvertedRate(rates, from, "CNH")).to3decimalPoint()),
        CurrencyRateItem(currency = "USD", value = (amount * getConvertedRate(rates, from, "USD")).to3decimalPoint()),
        CurrencyRateItem(currency = "BTC", value = (amount * getConvertedRate(rates, from, "BTC")).to3decimalPoint()),
        CurrencyRateItem(currency = "GBP", value = (amount * getConvertedRate(rates, from, "GBP")).to3decimalPoint()),
        CurrencyRateItem(currency = "SGD", value = (amount * getConvertedRate(rates, from, "SGD")).to3decimalPoint()),
        CurrencyRateItem(currency = "INR", value = (amount * getConvertedRate(rates, from, "INR")).to3decimalPoint()),
        CurrencyRateItem(currency = "AUD", value = (amount * getConvertedRate(rates, from, "AUD")).to3decimalPoint()),
        CurrencyRateItem(currency = "NZD", value = (amount * getConvertedRate(rates, from, "NZD")).to3decimalPoint()),
        CurrencyRateItem(currency = "CZK", value = (amount * getConvertedRate(rates, from, "CZK")).to3decimalPoint()),
        CurrencyRateItem(currency = "RON", value = (amount * getConvertedRate(rates, from, "RON")).to3decimalPoint()),
        CurrencyRateItem(currency = "PHP", value = (amount * getConvertedRate(rates, from, "PHP")).to3decimalPoint()),
        CurrencyRateItem(currency = "HKD", value = (amount * getConvertedRate(rates, from, "HKD")).to3decimalPoint()),
        CurrencyRateItem(currency = "ISK", value = (amount * getConvertedRate(rates, from, "ISK")).to3decimalPoint()),
        CurrencyRateItem(currency = "DKK", value = (amount * getConvertedRate(rates, from, "DKK")).to3decimalPoint()),
        CurrencyRateItem(currency = "SEK", value = (amount * getConvertedRate(rates, from, "SEK")).to3decimalPoint()),
        CurrencyRateItem(currency = "XPF", value = (amount * getConvertedRate(rates, from, "XPF")).to3decimalPoint()),
        CurrencyRateItem(currency = "XPT", value = (amount * getConvertedRate(rates, from, "XPT")).to3decimalPoint())
    )
}

fun fetchRatesAsList(rates: Map<String, Any>, rateObj: Rates, amount: Double = 0.0, from: String)
    : MutableList<CurrencyRateItem> {
    if (from.isEmpty()) return mutableListOf()
    val ratesList = mutableListOf<CurrencyRateItem>()
    rates.entries.forEach { rate -> ratesList.add(
        CurrencyRateItem(
            currency = rate.key,
            value = (amount * getConvertedRate(rateObj, from, rate.key)).to3decimalPoint()
        )
    )
    }
    return ratesList
}

fun <K, V> Map<K, V>.toRateInfoObject(): Rates {
    val rates = Rates()
    this.entries.forEach {
        when(it.key) {
            "AED" -> rates.AED = it.value.toString()
            "AFN" -> rates.AFN = it.value.toString()
            "ALL" -> rates.ALL = it.value.toString()
            "AMD" -> rates.AMD = it.value.toString()
            "ANG" -> rates.ANG = it.value.toString()
            "AOA" -> rates.AOA = it.value.toString()
            "ARS" -> rates.ARS = it.value.toString()
            "AUD" -> rates.AUD = it.value.toString()
            "AWG" -> rates.AWG = it.value.toString()
            "AZN" -> rates.AZN = it.value.toString()
            "BAM" -> rates.BAM = it.value.toString()
            "BBD" -> rates.BBD = it.value.toString()
            "BDT" -> rates.BDT = it.value.toString()
            "BGN" -> rates.BGN = it.value.toString()
            "BHD" -> rates.BHD = it.value.toString()
            "BIF" -> rates.BIF = it.value.toString()
            "BMD" -> rates.BMD = it.value.toString()
            "BND" -> rates.BND = it.value.toString()
            "BOB" -> rates.BOB = it.value.toString()
            "BRL" -> rates.BRL = it.value.toString()
            "BSD" -> rates.BSD = it.value.toString()
            "BTC" -> rates.BTC = it.value.toString()
            "BTN" -> rates.BTN = it.value.toString()
            "BWP" -> rates.BWP = it.value.toString()
            "BYN" -> rates.BYN = it.value.toString()
            "BZD" -> rates.BZD = it.value.toString()
            "CAD" -> rates.CAD = it.value.toString()
            "CDF" -> rates.CDF = it.value.toString()
            "CHF" -> rates.CHF = it.value.toString()
            "CLF" -> rates.CLF = it.value.toString()
            "CLP" -> rates.CLP = it.value.toString()
            "CNH" -> rates.CNH = it.value.toString()
            "CNY" -> rates.CNY = it.value.toString()
            "COP" -> rates.COP = it.value.toString()
            "CRC" -> rates.CRC = it.value.toString()
            "CUC" -> rates.CUC = it.value.toString()
            "CUP" -> rates.CUP = it.value.toString()
            "CVE" -> rates.CVE = it.value.toString()
            "CZK" -> rates.CZK = it.value.toString()
            "DJF" -> rates.DJF = it.value.toString()
            "DKK" -> rates.DKK = it.value.toString()
            "DOP" -> rates.DOP = it.value.toString()
            "DZD" -> rates.DZD = it.value.toString()
            "EGP" -> rates.EGP = it.value.toString()
            "ERN" -> rates.ERN = it.value.toString()
            "ETB" -> rates.ETB = it.value.toString()
            "EUR" -> rates.EUR = it.value.toString()
            "FJD" -> rates.FJD = it.value.toString()
            "FKP" -> rates.FKP = it.value.toString()
            "GBP" -> rates.GBP = it.value.toString()
            "GEL" -> rates.GEL = it.value.toString()
            "GGP" -> rates.GGP = it.value.toString()
            "GHS" -> rates.GHS = it.value.toString()
            "GIP" -> rates.GIP = it.value.toString()
            "GMD" -> rates.GMD = it.value.toString()
            "GNF" -> rates.GNF = it.value.toString()
            "GTQ" -> rates.GTQ = it.value.toString()
            "GYD" -> rates.GYD = it.value.toString()
            "HKD" -> rates.HKD = it.value.toString()
            "HNL" -> rates.HNL = it.value.toString()
            "HRK" -> rates.HRK = it.value.toString()
            "HTG" -> rates.HTG = it.value.toString()
            "HUF" -> rates.HUF = it.value.toString()// Code for HUF
            "IDR" -> rates.IDR = it.value.toString()// Code for IDR
            "ILS" -> rates.ILS = it.value.toString()// Code for ILS
            "IMP" -> rates.IMP = it.value.toString()// Code for IMP
            "INR" -> rates.INR = it.value.toString()// Code for INR
            "IQD" -> rates.IQD = it.value.toString()// Code for IQD
            "IRR" -> rates.IRR = it.value.toString()// Code for IRR
            "ISK" -> rates.ISK = it.value.toString()// Code for ISK
            "JEP" -> rates.JEP = it.value.toString()// Code for JEP
            "JMD" -> rates.JMD = it.value.toString()// Code for JMD
            "JOD" -> rates.JOD = it.value.toString()// Code for JOD
            "JPY" -> rates.JPY = it.value.toString()// Code for JPY
            "KES" -> rates.KES = it.value.toString()// Code for KES
            "KGS" -> rates.KGS = it.value.toString()// Code for KGS
            "KHR" -> rates.KHR = it.value.toString()// Code for KHR
            "KMF" -> rates.KMF = it.value.toString()// Code for KMF
            "KPW" -> rates.KPW = it.value.toString()// Code for KPW
            "KRW" -> rates.KRW = it.value.toString()// Code for KRW
            "KWD" -> rates.KWD = it.value.toString()// Code for KWD
            "KYD" -> rates.KYD = it.value.toString()// Code for KYD
            "KZT" -> rates.KZT = it.value.toString()// Code for KZT
            "LAK" -> rates.LAK = it.value.toString()// Code for LAK
            "LBP" -> rates.LBP = it.value.toString()// Code for LBP
            "LKR" -> rates.LKR = it.value.toString()// Code for LKR
            "LRD" -> rates.LRD = it.value.toString()// Code for LRD
            "LSL" -> rates.LSL = it.value.toString()// Code for LSL
            "LYD" -> rates.LYD = it.value.toString()// Code for LYD
            "MAD" -> rates.MAD = it.value.toString()// Code for MAD
            "MDL" -> rates.MDL = it.value.toString()// Code for MDL
            "MGA" -> rates.MGA = it.value.toString()// Code for MGA
            "MKD" -> rates.MKD = it.value.toString() // Code for MKD
            "MMK" -> rates.MMK = it.value.toString() // Code for MMK
            "MNT" -> rates.MNT = it.value.toString() // Code for MNT
            "MOP" -> rates.MOP = it.value.toString() // Code for MOP
            "MRU" -> rates.MRU = it.value.toString() // Code for MRU
            "MUR" -> rates.MUR = it.value.toString() // Code for MUR
            "MVR" -> rates.MVR = it.value.toString() // Code for MVR
            "MWK" -> rates.MWK = it.value.toString() // Code for MWK
            "MXN" -> rates.MXN = it.value.toString() // Code for MXN
            "MYR" -> rates.MYR = it.value.toString() // Code for MYR
            "MZN" -> rates.MZN = it.value.toString() // Code for MZN
            "NAD" -> rates.NAD = it.value.toString() // Code for NAD
            "NGN" -> rates.NGN = it.value.toString() // Code for NGN
            "NIO" -> rates.NIO = it.value.toString() // Code for NIO
            "NOK" -> rates.NOK = it.value.toString() // Code for NOK
            "NPR" -> rates.NPR = it.value.toString() // Code for NPR
            "NZD" -> rates.NZD = it.value.toString() // Code for NZD
            "OMR" -> rates.OMR = it.value.toString() // Code for OMR
            "PAB" -> rates.PAB = it.value.toString() // Code for PAB
            "PEN" -> rates.PEN = it.value.toString() // Code for PEN
            "PGK" -> rates.PGK = it.value.toString() // Code for PGK
            "PHP" -> rates.PHP = it.value.toString() // Code for PHP
            "PKR" -> rates.PKR = it.value.toString() // Code for PKR
            "PLN" -> rates.PLN = it.value.toString() // Code for PLN
            "PYG" -> rates.PYG = it.value.toString() // Code for PYG
            "QAR" -> rates.QAR = it.value.toString() // Code for QAR
            "RON" -> rates.RON = it.value.toString() // Code for RON
            "RSD" -> rates.RSD = it.value.toString() // Code for RSD
            "RUB" -> rates.RUB = it.value.toString() // Code for RUB
            "RWF" -> rates.RWF = it.value.toString() // Code for RWF
            "SAR" -> rates.SAR = it.value.toString() // Code for SAR
            "SBD" -> rates.SBD = it.value.toString() // Code for SBD
            "SCR" -> rates.SCR = it.value.toString() // Code for SCR
            "SDG" -> rates.SDG = it.value.toString() // Code for SDG
            "SEK" -> rates.SEK = it.value.toString() // Code for SEK
            "SGD" -> rates.SGD = it.value.toString() // Code for SGD
            "SHP" -> rates.SHP = it.value.toString() // Code for SHP
            "SLL" -> rates.SLL = it.value.toString() // Code for SLL
            "SOS" -> rates.SOS = it.value.toString() // Code for SOS
            "SRD" -> rates.SRD = it.value.toString() // Code for SRD
            "SSP" -> rates.SSP = it.value.toString() // Code for SSP
            "STD" -> rates.STD = it.value.toString() // Code for STD
            "STN" -> rates.STN = it.value.toString() // Code for STN
            "SVC" -> rates.SVC = it.value.toString() // Code for SVC
            "SYP" -> rates.SYP = it.value.toString() // Code for SYP
            "SZL" -> rates.SZL = it.value.toString() // Code for SZL
            "THB" -> rates.THB = it.value.toString() // Code for THB
            "TJS" -> rates.TJS = it.value.toString() // Code for TJS
            "TMT" -> rates.TMT = it.value.toString() // Code for TMT
            "TND" -> rates.TND = it.value.toString() // Code for TND
            "TOP" -> rates.TOP = it.value.toString() // Code for TOP
            "TRY" -> rates.TRY = it.value.toString() // Code for TRY
            "TTD" -> rates.TTD = it.value.toString() // Code for TTD
            "TWD" -> rates.TWD = it.value.toString() // Code for TWD
            "TZS" -> rates.TZS = it.value.toString() // Code for TZS
            "UAH" -> rates.UAH = it.value.toString() // Code for UAH
            "UGX" -> rates.UGX = it.value.toString() // Code for UGX
            "USD" -> rates.USD = it.value.toString() // Code for USD
            "UYU" -> rates.UYU = it.value.toString() // Code for UYU
            "UZS" -> rates.UZS = it.value.toString() // Code for UZS
            "VES" -> rates.VES = it.value.toString() // Code for VES
            "VND" -> rates.VND = it.value.toString() // Code for VND
            "VUV" -> rates.VUV = it.value.toString() // Code for VUV
            "WST" -> rates.WST = it.value.toString() // Code for WST
            "XAF" -> rates.XAF = it.value.toString() // Code for XAF
            "XAG" -> rates.XAG = it.value.toString() // Code for XAG
            "XAU" -> rates.XAU = it.value.toString() // Code for XAU
            "XCD" -> rates.XCD = it.value.toString() // Code for XCD
            "XDR" -> rates.XDR = it.value.toString() // Code for XDR
            "XOF" -> rates.XOF = it.value.toString() // Code for XOF
            "XPD" -> rates.XPD = it.value.toString() // Code for XPD
            "XPF" -> rates.XPF = it.value.toString() // Code for XPF
            "XPT" -> rates.XPT = it.value.toString() // Code for XPT
            "YER" -> rates.YER = it.value.toString() // Code for YER
            "ZAR" -> rates.ZAR = it.value.toString() // Code for ZAR
            "ZMW" -> rates.ZMW = it.value.toString() // Code for ZMW
            "ZWL" -> rates.ZWL = it.value.toString() // Code for ZWL
        }
    }
    return rates
}

inline fun <reified T : Any> T.asMap() : Map<String, Any> {
    val props = T::class.memberProperties.associateBy { it.name }
    return props.keys.associateWith { props[it]?.get(this)!! }
}