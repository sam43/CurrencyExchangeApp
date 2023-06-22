package com.sam43.currencyexchangeapp.utils

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.Rates
import kotlin.reflect.full.memberProperties

fun getRateForCurrency(base: String, rates: Rates): String = when (base) {
    "AED" -> rates.AED
    "AFN" -> rates.AFN
    "ALL" -> rates.ALL
    "AMD" -> rates.AMD
    "ANG" -> rates.ANG
    "AOA" -> rates.AOA
    "ARS" -> rates.ARS
    "AUD" -> rates.AUD
    "AWG" -> rates.AWG
    "AZN" -> rates.AZN
    "BAM" -> rates.BAM
    "BBD" -> rates.BBD
    "BDT" -> rates.BDT
    "BGN" -> rates.BGN
    "BHD" -> rates.BHD
    "BIF" -> rates.BIF
    "BMD" -> rates.BMD
    "BND" -> rates.BND
    "BOB" -> rates.BOB
    "BRL" -> rates.BRL
    "BSD" -> rates.BSD
    "BTC" -> rates.BTC
    "BTN" -> rates.BTN
    "BWP" -> rates.BWP
    "BYN" -> rates.BYN
    "BZD" -> rates.BZD
    "CAD" -> rates.CAD
    "CDF" -> rates.CDF
    "CHF" -> rates.CHF
    "CLF" -> rates.CLF
    "CLP" -> rates.CLP
    "CNH" -> rates.CNH
    "CNY" -> rates.CNY
    "COP" -> rates.COP
    "CRC" -> rates.CRC
    "CUC" -> rates.CUC
    "CUP" -> rates.CUP
    "CVE" -> rates.CVE
    "CZK" -> rates.CZK
    "DJF" -> rates.DJF
    "DKK" -> rates.DKK
    "DOP" -> rates.DOP
    "DZD" -> rates.DZD
    "EGP" -> rates.EGP
    "ERN" -> rates.ERN
    "ETB" -> rates.ETB
    "EUR" -> rates.EUR
    "FJD" -> rates.FJD
    "FKP" -> rates.FKP
    "GBP" -> rates.GBP
    "GEL" -> rates.GEL
    "GGP" -> rates.GGP
    "GHS" -> rates.GHS
    "GIP" -> rates.GIP
    "GMD" -> rates.GMD
    "GNF" -> rates.GNF
    "GTQ" -> rates.GTQ
    "GYD" -> rates.GYD
    "HKD" -> rates.HKD
    "HNL" -> rates.HNL
    "HRK" -> rates.HRK
    "HTG" -> rates.HTG
    "HUF" -> rates.HUF// Code for HUF
    "IDR" -> rates.IDR// Code for IDR
    "ILS" -> rates.ILS// Code for ILS
    "IMP" -> rates.IMP// Code for IMP
    "INR" -> rates.INR// Code for INR
    "IQD" -> rates.IQD// Code for IQD
    "IRR" -> rates.IRR// Code for IRR
    "ISK" -> rates.ISK// Code for ISK
    "JEP" -> rates.JEP// Code for JEP
    "JMD" -> rates.JMD// Code for JMD
    "JOD" -> rates.JOD// Code for JOD
    "JPY" -> rates.JPY// Code for JPY
    "KES" -> rates.KES// Code for KES
    "KGS" -> rates.KGS// Code for KGS
    "KHR" -> rates.KHR// Code for KHR
    "KMF" -> rates.KMF// Code for KMF
    "KPW" -> rates.KPW// Code for KPW
    "KRW" -> rates.KRW// Code for KRW
    "KWD" -> rates.KWD// Code for KWD
    "KYD" -> rates.KYD// Code for KYD
    "KZT" -> rates.KZT// Code for KZT
    "LAK" -> rates.LAK// Code for LAK
    "LBP" -> rates.LBP// Code for LBP
    "LKR" -> rates.LKR// Code for LKR
    "LRD" -> rates.LRD// Code for LRD
    "LSL" -> rates.LSL// Code for LSL
    "LYD" -> rates.LYD// Code for LYD
    "MAD" -> rates.MAD// Code for MAD
    "MDL" -> rates.MDL// Code for MDL
    "MGA" -> rates.MGA// Code for MGA
    "MKD" -> rates.MKD // Code for MKD
    "MMK" -> rates.MMK // Code for MMK
    "MNT" -> rates.MNT // Code for MNT
    "MOP" -> rates.MOP // Code for MOP
    "MRU" -> rates.MRU // Code for MRU
    "MUR" -> rates.MUR // Code for MUR
    "MVR" -> rates.MVR // Code for MVR
    "MWK" -> rates.MWK // Code for MWK
    "MXN" -> rates.MXN // Code for MXN
    "MYR" -> rates.MYR // Code for MYR
    "MZN" -> rates.MZN // Code for MZN
    "NAD" -> rates.NAD // Code for NAD
    "NGN" -> rates.NGN // Code for NGN
    "NIO" -> rates.NIO // Code for NIO
    "NOK" -> rates.NOK // Code for NOK
    "NPR" -> rates.NPR // Code for NPR
    "NZD" -> rates.NZD // Code for NZD
    "OMR" -> rates.OMR // Code for OMR
    "PAB" -> rates.PAB // Code for PAB
    "PEN" -> rates.PEN // Code for PEN
    "PGK" -> rates.PGK // Code for PGK
    "PHP" -> rates.PHP // Code for PHP
    "PKR" -> rates.PKR // Code for PKR
    "PLN" -> rates.PLN // Code for PLN
    "PYG" -> rates.PYG // Code for PYG
    "QAR" -> rates.QAR // Code for QAR
    "RON" -> rates.RON // Code for RON
    "RSD" -> rates.RSD // Code for RSD
    "RUB" -> rates.RUB // Code for RUB
    "RWF" -> rates.RWF // Code for RWF
    "SAR" -> rates.SAR // Code for SAR
    "SBD" -> rates.SBD // Code for SBD
    "SCR" -> rates.SCR // Code for SCR
    "SDG" -> rates.SDG // Code for SDG
    "SEK" -> rates.SEK // Code for SEK
    "SGD" -> rates.SGD // Code for SGD
    "SHP" -> rates.SHP // Code for SHP
    "SLL" -> rates.SLL // Code for SLL
    "SOS" -> rates.SOS // Code for SOS
    "SRD" -> rates.SRD // Code for SRD
    "SSP" -> rates.SSP // Code for SSP
    "STD" -> rates.STD // Code for STD
    "STN" -> rates.STN // Code for STN
    "SVC" -> rates.SVC // Code for SVC
    "SYP" -> rates.SYP // Code for SYP
    "SZL" -> rates.SZL // Code for SZL
    "THB" -> rates.THB // Code for THB
    "TJS" -> rates.TJS // Code for TJS
    "TMT" -> rates.TMT // Code for TMT
    "TND" -> rates.TND // Code for TND
    "TOP" -> rates.TOP // Code for TOP
    "TRY" -> rates.TRY // Code for TRY
    "TTD" -> rates.TTD // Code for TTD
    "TWD" -> rates.TWD // Code for TWD
    "TZS" -> rates.TZS // Code for TZS
    "UAH" -> rates.UAH // Code for UAH
    "UGX" -> rates.UGX // Code for UGX
    "USD" -> rates.USD // Code for USD
    "UYU" -> rates.UYU // Code for UYU
    "UZS" -> rates.UZS // Code for UZS
    "VES" -> rates.VES // Code for VES
    "VND" -> rates.VND // Code for VND
    "VUV" -> rates.VUV // Code for VUV
    "WST" -> rates.WST // Code for WST
    "XAF" -> rates.XAF // Code for XAF
    "XAG" -> rates.XAG // Code for XAG
    "XAU" -> rates.XAU // Code for XAU
    "XCD" -> rates.XCD // Code for XCD
    "XDR" -> rates.XDR // Code for XDR
    "XOF" -> rates.XOF // Code for XOF
    "XPD" -> rates.XPD // Code for XPD
    "XPF" -> rates.XPF // Code for XPF
    "XPT" -> rates.XPT // Code for XPT
    "YER" -> rates.YER // Code for YER
    "ZAR" -> rates.ZAR // Code for ZAR
    "ZMW" -> rates.ZMW // Code for ZMW
    "ZWL" -> rates.ZWL // Code for ZWL
    else -> "0.0"
}

fun unitConvertedRate(rates: Rates, from: String, to: String): Double =
    if (from == to) 1.0 else (1.0/(getRateForCurrency(from, rates)).toDouble()) * getRateForCurrency(to, rates).toDouble()

fun fetchRatesAsList(rates: Map<String, Any>, rateObj: Rates, amount: Double = 0.0, from: String)
    : MutableList<CurrencyRateItem> {
    if (from.isEmpty()) return mutableListOf()
    val ratesList = mutableListOf<CurrencyRateItem>()
    rates.entries.forEach { rate -> ratesList.add(
        CurrencyRateItem(
            currency = rate.key,
            value = (amount * unitConvertedRate(rateObj, from, rate.key)).toString().to3decimalPoint()
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