package com.sam43.currencyexchangeapp.data.remote


import androidx.annotation.Keep
import com.sam43.currencyexchangeapp.data.models.Rates

@Keep
data class RatesDto(
    val AED: Double,
    val AFN: Double,
    val ALL: Double,
    val AMD: Double,
    val ANG: Double,
    val AOA: Double,
    val ARS: Double,
    val AUD: Double,
    val AWG: Double,
    val AZN: Double,
    val BAM: Double,
    val BBD: Int,
    val BDT: Double,
    val BGN: Double,
    val BHD: Double,
    val BIF: Double,
    val BMD: Int,
    val BND: Double,
    val BOB: Double,
    val BRL: Double,
    val BSD: Int,
    val BTC: Double,
    val BTN: Double,
    val BWP: Double,
    val BYN: Double,
    val BZD: Double,
    val CAD: Double,
    val CDF: Double,
    val CHF: Double,
    val CLF: Double,
    val CLP: Double,
    val CNH: Double,
    val CNY: Double,
    val COP: Double,
    val CRC: Double,
    val CUC: Int,
    val CUP: Double,
    val CVE: Double,
    val CZK: Double,
    val DJF: Double,
    val DKK: Double,
    val DOP: Double,
    val DZD: Double,
    val EGP: Double,
    val ERN: Int,
    val ETB: Double,
    val EUR: Double,
    val FJD: Double,
    val FKP: Double,
    val GBP: Double,
    val GEL: Double,
    val GGP: Double,
    val GHS: Double,
    val GIP: Double,
    val GMD: Double,
    val GNF: Double,
    val GTQ: Double,
    val GYD: Double,
    val HKD: Double,
    val HNL: Double,
    val HRK: Double,
    val HTG: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val IMP: Double,
    val INR: Double,
    val IQD: Double,
    val IRR: Int,
    val ISK: Double,
    val JEP: Double,
    val JMD: Double,
    val JOD: Double,
    val JPY: Double,
    val KES: Double,
    val KGS: Double,
    val KHR: Double,
    val KMF: Double,
    val KPW: Int,
    val KRW: Double,
    val KWD: Double,
    val KYD: Double,
    val KZT: Double,
    val LAK: Double,
    val LBP: Double,
    val LKR: Double,
    val LRD: Double,
    val LSL: Double,
    val LYD: Double,
    val MAD: Double,
    val MDL: Double,
    val MGA: Double,
    val MKD: Double,
    val MMK: Double,
    val MNT: Int,
    val MOP: Double,
    val MRU: Double,
    val MUR: Double,
    val MVR: Double,
    val MWK: Double,
    val MXN: Double,
    val MYR: Double,
    val MZN: Double,
    val NAD: Double,
    val NGN: Double,
    val NIO: Double,
    val NOK: Double,
    val NPR: Double,
    val NZD: Double,
    val OMR: Double,
    val PAB: Int,
    val PEN: Double,
    val PGK: Double,
    val PHP: Double,
    val PKR: Double,
    val PLN: Double,
    val PYG: Double,
    val QAR: Double,
    val RON: Double,
    val RSD: Double,
    val RUB: Double,
    val RWF: Double,
    val SAR: Double,
    val SBD: Double,
    val SCR: Double,
    val SDG: Double,
    val SEK: Double,
    val SGD: Double,
    val SHP: Double,
    val SLL: Int,
    val SOS: Double,
    val SRD: Double,
    val SSP: Double,
    val STD: Double,
    val STN: Double,
    val SVC: Double,
    val SYP: Double,
    val SZL: Double,
    val THB: Double,
    val TJS: Double,
    val TMT: Double,
    val TND: Double,
    val TOP: Double,
    val TRY: Double,
    val TTD: Double,
    val TWD: Double,
    val TZS: Int,
    val UAH: Double,
    val UGX: Double,
    val USD: Int,
    val UYU: Double,
    val UZS: Double,
    val VES: Double,
    val VND: Double,
    val VUV: Double,
    val WST: Double,
    val XAF: Double,
    val XAG: Double,
    val XAU: Double,
    val XCD: Double,
    val XDR: Double,
    val XOF: Double,
    val XPD: Double,
    val XPF: Double,
    val XPT: Double,
    val YER: Double,
    val ZAR: Double,
    val ZMW: Double,
    val ZWL: Int
) {
    fun toRateInfo(): Rates {
        return Rates(
            AED, AFN, ALL, AMD, ANG, AOA, ARS, AUD, AWG, AZN, BAM, BDT, BGN, BHD, BIF, BMD, BND, BOB, BRL, BTC, BTN, BWP, BYN, BZD, CAD,
            CDF, CHF, CLF, CLP, CNH, CNY, COP, CRC, CUP, CVE, CZK, DJF, DKK, DOP, DZD, EGP, ERN.toDouble(), ETB, EUR, FJD, FKP, GBP, GEL, GGP, GHS, GIP, GMD, GNF, GTQ, GYD, HKD, HNL, HRK, HTG, HUF, ILS, IMP, INR, IQD, ISK, JEP, JMD, JOD, JPY, KES, KGS, KHR, KMF, KRW, KWD, KYD, KZT, LAK, LBP, LKR, LRD, LSL, LYD, MAD, MDL,
            MGA, MKD, MMK, MNT.toDouble(), MOP, MRU, MUR, MVR, MWK, MXN, MYR, MZN, NAD, NGN, NIO, NOK, NPR, NZD, OMR, PEN, PGK, PHP, PKR, PLN, PYG, QAR, RON, RSD, RUB, RWF, SAR, SBD, SCR, SDG, SEK, SGD, SHP, SLL.toDouble(), SOS, SRD, SSP, STD, STN, SVC, SYP, SZL, THB, TJS, TMT, TND, TOP, TRY, TTD, TWD, UAH, UGX, USD, UYU, UZS, VES, VUV, WST, XAF, XAG, XAU, XCD, XDR, XOF, XPD, XPF, XPT, YER, ZAR, ZMW
        )
    }
}