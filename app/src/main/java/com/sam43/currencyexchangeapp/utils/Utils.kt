package com.sam43.currencyexchangeapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}