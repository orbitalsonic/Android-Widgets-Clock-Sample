package com.orbitalsonic.androidwidigets.clock

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ClockFormatter {

    fun getTime(): String {
        val sdf = SimpleDateFormat("hh:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    fun getDate(): String {
        val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
        return sdf.format(Date())
    }
}