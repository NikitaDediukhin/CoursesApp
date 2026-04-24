package com.example.feature_main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_main.domain.model.Course
import com.example.feature_main.domain.usecase.ObserveCoursesUseCase
import com.example.feature_main.domain.usecase.SyncCoursesUseCase
import com.example.feature_main.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val observeCoursesUseCase: ObserveCoursesUseCase,
    private val syncCoursesUseCase: SyncCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        observeCourses()
        syncCourses()
    }

    private fun observeCourses() {
        viewModelScope.launch {
            observeCoursesUseCase()
                .catch { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = if (_uiState.value.courses.isEmpty()) {
                            throwable.message
                        } else {
                            null
                        }
                    )
                }
                .collect { courses ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        courses = courses,
                        error = null
                    )
                }
        }
    }

    private fun syncCourses() {
        viewModelScope.launch {
            if (_uiState.value.courses.isEmpty()) {
                _uiState.value = _uiState.value.copy(isLoading = true)
            }

            runCatching { syncCoursesUseCase() }
                .onFailure { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = if (_uiState.value.courses.isEmpty()) {
                            throwable.message
                        } else {
                            null
                        }
                    )
                }
        }
    }

    fun onFavoriteClick(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(
                courseId = course.id,
                hasLike = !course.hasLike
            )
        }
    }

    fun onSortClick() {
        val sortedCourses = _uiState.value.courses.sortedByDescending { it.publishDate }
        _uiState.value = _uiState.value.copy(courses = sortedCourses)
    }
}