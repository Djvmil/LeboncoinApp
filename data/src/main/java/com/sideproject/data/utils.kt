package com.sideproject.data

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object utils {

    fun toSimpleString(date: Date? = null) = with(date ?: Date()) {
        SimpleDateFormat("dd/MM/yyy", Locale.FRANCE).format(this)
    }
}
