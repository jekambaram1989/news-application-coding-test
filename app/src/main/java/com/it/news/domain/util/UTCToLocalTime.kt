package com.it.news.domain.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.toLocalDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Set input format to UTC
    val outputFormat = SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(this) // Parse the input date string
    return outputFormat.format(date!!) // Format the date into the desired output
}