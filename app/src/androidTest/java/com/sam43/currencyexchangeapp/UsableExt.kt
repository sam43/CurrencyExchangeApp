package com.sam43.currencyexchangeapp

import com.sam43.currencyexchangeapp.data.models.Rates

fun dummyRatesAndroidTest() = Rates(FJD="2.21", MXN="17.145504", STD="22823.990504", SCR="13.276785", CDF="2371.138744", BBD="2", GTQ="7.839187", CLP="802.55", HNL="24.463758", UGX="3681.120746", ZAR="18.376988", TND="3.0765", STN="22.296037", CUC="1", BSD="1", SLL="17665", SDG="601.5", IQD="1301.653839", CUP="25.75", GMD="59.4", TWD="30.9448", RSD="106.753967", DOP="54.620736", KMF="450.075124", MYR="4.6505", FKP="0.783762", XOF="597.010008", GEL="2.615", BTC="0.000032960767", UYU="37.942956", MAD="9.914911", CVE="101.030695", TOP="2.346243", AZN="1.7", OMR="0.38497", PGK="3.540065", KES="140.35", SEK="10.664717", CNH="7.182275", BTN="81.984391", UAH="36.928154", GNF="8620.450345", ERN="15", MZN="63.875002", SVC="8.749485", ARS="251.896005", QAR="3.641", IRR="42250", XPD="0.00075164", CNY="7.1795", THB="34.8905", UZS="11503.32704", XPF="108.608101", MRU="34.559148", BDT="108.182619", LYD="4.797604", BMD="1", KWD="0.307174", PHP="55.652002", XPT="0.00106183", RUB="83.963056", PYG="7255.925349", ISK="134.6", JMD="154.082116", COP="4124.638504", MKD="56.447252", USD="1", DZD="135.503978", PAB="1", GGP="0.783762", SGD="1.341806", ETB="54.612275", JEP="0.783762", KGS="87.3042", SOS="564.862851", VUV="118.979", LAK="18262.712029", BND="1.335396", XAF="597.010008", LRD="177.25002", XAG="0.04427698", CHF="0.89274", HRK="6.857044", ALL="97.282646", DJF="176.910575", VES="27.265875", ZMW="17.294444", TZS="2398", VND="23510", XAU="0.00051805", AUD="1.478497", ILS="3.62231", GHS="11.252673", GYD="211.479226", KPW="900", BOB="6.909499", KHR="4098.850553", MDL="17.929654", IDR="14927", KYD="0.83328", AMD="384.584086", BWP="13.231105", SHP="0.783762", TRY="23.5675", LBP="15155.501901", TJS="10.918998", JOD="0.7094", AED="3.673075", HKD="7.827532", RWF="1142.283979", EUR="0.910136", LSL="18.258815", DKK="6.778696", CAD="1.316524", BGN="1.779085", MMK="2099.820837", MUR="45.449996", NOK="10.663394", SYP="2512.53", IMP="0.783762", ZWL="322", GIP="0.783762", RON="4.5169", LKR="306.057793", NGN="755.5", CRC="540.322494", CZK="21.603598", PKR="286.842105", XCD="2.70255", ANG="1.802097", HTG="138.989888", BHD="0.376928", KZT="448.967081", SRD="37.9", SZL="18.242708", SAR="3.751393", TTD="6.785109", YER="250.300053", MVR="15.345", AFN="85.315691", INR="81.9475", AWG="1.8025", KRW="1293.929262", NPR="131.175149", JPY="141.77816667", MNT="3519", AOA="757.6428", PLN="4.038294", GBP="0.783762", SBD="8.323692", BYN="2.523834", HUF="336.624401", BIF="2825.216203", MWK="1018.611507", MGA="4509.244605", XDR="0.74573", BZD="2.015477", BAM="1.791108", EGP="30.8997", MOP="8.06418", NAD="18.32", SSP="130.26", NIO="36.558565", PEN="3.61214", NZD="1.615455", WST="2.72551", TMT="3.51", CLF="0.029085", BRL="4.7637")
fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}