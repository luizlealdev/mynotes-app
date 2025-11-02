package dev.luizleal.mynotes.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun LocalDateTime.formatTo(pattern: String = "dd MMM yyyy"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return formatter.format(this)
}