package com.example.feature_main.presentation.main

import com.example.feature_main.domain.model.Course

data class MainUiState(
    val isLoading: Boolean = false,
    val courses: List<Course> = emptyList(),
    val error: String? = null
)