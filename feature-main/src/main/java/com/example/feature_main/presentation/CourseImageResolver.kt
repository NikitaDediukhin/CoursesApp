package com.example.feature_main.presentation

import com.example.core_ui.R
import kotlin.math.absoluteValue

private val courseImages = listOf(
    R.drawable.course_placeholder_1,
    R.drawable.course_placeholder_2,
    R.drawable.course_placeholder_3
)

fun resolveCourseImage(courseId: Int): Int {
    return courseImages[courseId.absoluteValue % courseImages.size]
}