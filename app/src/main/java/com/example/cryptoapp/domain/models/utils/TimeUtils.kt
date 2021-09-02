package com.example.cryptoapp.domain.models.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimestampToTime(timestamp: Long?): String {
    val secToMillisec = 1000
    val stamp = timestamp?.times(secToMillisec)?.let { Timestamp(it) }
    val date = stamp?.let { Date(it.time) }
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}