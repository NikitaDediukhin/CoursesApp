package com.example.feature_main.presentation.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_main.domain.model.Course
import com.example.feature_main.domain.usecase.ObserveFavoriteCoursesUseCase
import com.example.feature_main.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    observeFavoriteCoursesUseCase: ObserveFavoriteCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    val courses: StateFlow<List<Course>> = observeFavoriteCoursesUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun onFavoriteClick(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(
                courseId = course.id,
                hasLike = !course.hasLike
            )
        }
    }
}