package com.example.feature_main.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun formatCourseDate(date: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))

        inputFormat.parse(date)?.let { parsedDate ->
            outputFormat.format(parsedDate)
                .replace(Regex("(?<=\\d\\s)[а-яё]")) {
                    it.value.uppercase()
                }
        } ?: date
    } catch (e: Exception) {
        date
    }
}