package com.sam43.currencyexchangeapp

import com.sam43.currencyexchangeapp.data.models.CurrencyRateItem
import com.sam43.currencyexchangeapp.data.models.CurrencyResponse
import com.sam43.currencyexchangeapp.data.models.Rates
import com.sam43.currencyexchangeapp.data.remote.RatesDto
import com.sam43.currencyexchangeapp.utils.AppConstants
import com.sam43.currencyexchangeapp.utils.getConvertedRate
import com.sam43.currencyexchangeapp.utils.getRatesAsList
import com.sam43.currencyexchangeapp.utils.to3decimalPoint
import io.mockk.every
import io.mockk.mockk

fun dummyRatesTest() = Rates(aED=3.6731, aFN=88.999995, aLL=114.902228, aMD=422.327295, aNG=1.801893, aOA=434.36325, aRS=122.467392, aUD=1.455625, aWG=1.8005, aZN=1.7, bAM=1.873957, bDT=94.317169, bGN=1.876368, bHD=0.376973, bIF=2032.0, bMD=1, bND=1.391518, bOB=6.883719, bRL=5.1397, bTC=4.4482222E-5, bTN=78.036557, bWP=12.298022, bYN=3.373895, bZD=2.015388, cAD=1.295284, cDF=2005.0, cHF=1.002636, cLF=0.031475, cLP=867.1, cNH=6.75701, cNY=6.741, cOP=3971.17, cRC=682.916314, cUP=25.75, cVE=106.225, cZK=23.7472, dJF=177.996866, dKK=7.13869, dOP=55.05, dZD=146.527208, eGP=18.741, eRN=15.000001, eTB=52.029953, eUR=0.959674, fJD=2.2032, fKP=0.833743, gBP=0.833743, gEL=2.94, gGP=0.833743, gHS=7.948585, gIP=0.833743, gMD=54.15, gNF=8850.0, gTQ=7.728518, gYD=209.173243, hKD=7.85005, hNL=24.565002, hRK=7.2196, hTG=115.011216, hUF=385.826435, iLS=3.473825, iMP=0.833743, iNR=78.014803, iQD=1459.249316, iSK=132.15, jEP=0.833743, jMD=152.906066, jOD=0.709, jPY=134.98216667, kES=117.2, kGS=79.500507, kHR=4061.2004, kMF=471.620221, kRW=1291.226523, kWD=0.307497, kYD=0.833206, kZT=437.370261, lAK=14702.201674, lBP=1510.17822, lKR=360.104799, lRD=151.999978, lSL=16.075713, lYD=4.823988, mAD=10.046086, mDL=19.098633, mGA=4060.761082, mKD=58.726373, mMK=1851.151781, mNT=3124.087599, mOP=8.083895, mRU=36.712978, mUR=44.400304, mVR=15.36, mWK=1021.413253, mXN=20.62429, mYR=4.4225, mZN=63.900001, nAD=16.09, nGN=419.672884, nIO=35.843706, nOK=10.022737, nPR=124.858187, nZD=1.610617, oMR=0.384994, pEN=3.778457, pGK=3.568239, pHP=53.279003, pKR=206.211559, pLN=4.472482, pYG=6873.03188, qAR=3.671157, rON=4.7438, rSD=112.63, rUB=58.750005, rWF=1021.21146, sAR=3.751709, sBD=8.10712, sCR=12.844943, sDG=456.5, sEK=10.212826, sGD=1.393346, sHP=0.833743, sLL=13130.0, sOS=578.433674, sRD=21.85, sSP=130.26, sTD=23263.0, sTN=23.7, sVC=8.748934, sYP=2512.53, sZL=16.075809, tHB=35.035292, tJS=10.898081, tMT=3.51, tND=3.0855, tOP=2.344563, tRY=17.279, tTD=6.786371, tWD=29.694002, uAH=29.538455, uGX=3754.368852, uSD=1, uYU=40.054231, uZS=10973.257095, vES=5.30395, vUV=116.538833, wST=2.661237, xAF=629.504767, xAG=0.04777146, xAU=5.5173E-4, xCD=2.70255, xDR=0.730762, xOF=629.504767, xPD=5.5312E-4, xPF=114.51955, xPT=0.00108344, yER=250.299955, zAR=16.059074, zMW=16.897008)
fun dummyRatesTestDto() = RatesDto(aED=3.6731, aFN=88.999995, aLL=114.902228, aMD=422.327295, aNG=1.801893, aOA=434.36325, aRS=122.467392, aUD=1.455625, aWG=1.8005, aZN=1.7, bAM=1.873957, bDT=94.317169, bGN=1.876368, bHD=0.376973, bIF=2032.0, bMD=1, bND=1.391518, bOB=6.883719, bRL=5.1397, bTC=4.4482222E-5, bTN=78.036557, bWP=12.298022, bYN=3.373895, bZD=2.015388, cAD=1.295284, cDF=2005.0, cHF=1.002636, cLF=0.031475, cLP=867.1, cNH=6.75701, cNY=6.741, cOP=3971.17, cRC=682.916314, cUP=25.75, cVE=106.225, cZK=23.7472, dJF=177.996866, dKK=7.13869, dOP=55.05, dZD=146.527208, eGP=18.741, eRN=15.000001, eTB=52.029953, eUR=0.959674, fJD=2.2032, fKP=0.833743, gBP=0.833743, gEL=2.94, gGP=0.833743, gHS=7.948585, gIP=0.833743, gMD=54.15, gNF=8850.0, gTQ=7.728518, gYD=209.173243, hKD=7.85005, hNL=24.565002, hRK=7.2196, hTG=115.011216, hUF=385.826435, iLS=3.473825, iMP=0.833743, iNR=78.014803, iQD=1459.249316, iSK=132.15, jEP=0.833743, jMD=152.906066, jOD=0.709, jPY=134.98216667, kES=117.2, kGS=79.500507, kHR=4061.2004, kMF=471.620221, kRW=1291.226523, kWD=0.307497, kYD=0.833206, kZT=437.370261, lAK=14702.201674, lBP=1510.17822, lKR=360.104799, lRD=151.999978, lSL=16.075713, lYD=4.823988, mAD=10.046086, mDL=19.098633, mGA=4060.761082, mKD=58.726373, mMK=1851.151781, mNT=3124.087599, mOP=8.083895, mRU=36.712978, mUR=44.400304, mVR=15.36, mWK=1021.413253, mXN=20.62429, mYR=4.4225, mZN=63.900001, nAD=16.09, nGN=419.672884, nIO=35.843706, nOK=10.022737, nPR=124.858187, nZD=1.610617, oMR=0.384994, pEN=3.778457, pGK=3.568239, pHP=53.279003, pKR=206.211559, pLN=4.472482, pYG=6873.03188, qAR=3.671157, rON=4.7438, rSD=112.63, rUB=58.750005, rWF=1021.21146, sAR=3.751709, sBD=8.10712, sCR=12.844943, sDG=456.5, sEK=10.212826, sGD=1.393346, sHP=0.833743, sLL=13130.0, sOS=578.433674, sRD=21.85, sSP=130.26, sTD=23263.0, sTN=23.7, sVC=8.748934, sYP=2512.53, sZL=16.075809, tHB=35.035292, tJS=10.898081, tMT=3.51, tND=3.0855, tOP=2.344563, tRY=17.279, tTD=6.786371, tWD=29.694002, uAH=29.538455, uGX=3754.368852, uSD=1, uYU=40.054231, uZS=10973.257095, vES=5.30395, vUV=116.538833, wST=2.661237, xAF=629.504767, xAG=0.04777146, xAU=5.5173E-4, xCD=2.70255, xDR=0.730762, xOF=629.504767, xPD=5.5312E-4, xPF=114.51955, xPT=0.00108344, yER=250.299955, zAR=16.059074, zMW=16.897008)

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