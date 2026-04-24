package com.example.feature_main.domain.usecase

import com.example.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class ObserveCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    operator fun invoke() = repository.observeCourses()
}