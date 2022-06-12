package com.sam43.currencyexchangeapp.utils

import android.content.Context
import android.widget.Toast


fun Context.showLongToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Double.to3decimalPoint(): String = String.format("%.3f", this).trimTrailingZero()


fun String?.trimTrailingZero(): String = if (!this.isNullOrEmpty()) {
    if (this.indexOf(".") < 0) {
        this

    } else {
        this.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

} else ({
    this
}).toString()