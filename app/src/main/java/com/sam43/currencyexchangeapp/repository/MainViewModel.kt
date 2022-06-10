package com.sam43.currencyexchangeapp.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.utils.DispatcherProvider
import com.sam43.currencyexchangeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
): ViewModel() {

    sealed class CurrencyEvent {
        class Success(val resultText: String): CurrencyEvent()
        class SuccessResponse(val response: CurrencyResponse?): CurrencyEvent()
        class Failure(val errorText: String): CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val TAG = "MainViewModel"
    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: StateFlow<CurrencyEvent> = _conversion

    fun convert(
        amountStr: String,
        fromCurrency: String? = "USD"
    ) {
        val fromAmount = amountStr.toFloatOrNull()
        if(fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading
            when(val ratesResponse = repository.getRates(fromCurrency/*option to set custom 'base' currency param (need subscription for this)*/)) {
                is Resource.Error -> _conversion.value =
                    CurrencyEvent.Failure(ratesResponse.message!!)
                is Resource.Success -> _conversion.value =
                    CurrencyEvent.SuccessResponse(ratesResponse.data)
            }
        }
    }

    private fun getRateForCurrency(currency: String, rates: Rates): Double? = when (currency) {
        "CAD" -> rates.cAD
        "HKD" -> rates.hKD
        "ISK" -> rates.iSK
        "EUR" -> rates.eUR
        "PHP" -> rates.pHP
        "DKK" -> rates.dKK
        "HUF" -> rates.hUF
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
        "RSD" -> rates.nIO
        "RWF" -> rates.nPR
        "SAR" -> rates.oMR
//        "SBD" -> rates.pAB?.toDouble()
        "SCR" -> rates.pEN
        "SDG" -> rates.pGK
        "SHP" -> rates.pKR
        "SLL" -> rates.pYG
        "SOS" -> rates.qAR
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
        else -> null
    }
}