package com.alvarohidalgo.mybank.base.presentation

import android.content.Context
import androidx.core.content.ContextCompat
import com.alvarohidalgo.mybank.R


fun Double.getBalanceTextColor(context: Context): Int {
    return when {
        this > 0 -> ContextCompat.getColor(context, R.color.green)
        else -> ContextCompat.getColor(context, R.color.red)
    }
}