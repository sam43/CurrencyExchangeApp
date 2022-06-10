package com.sam43.currencyexchangeapp.utils

import android.content.Context
import android.widget.Toast


fun Context.showLongToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Double.to5decimalPoint(): String = String.format("%.3f", this)