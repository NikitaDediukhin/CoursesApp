package com.example.feature_main.domain.usecase

import com.example.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class SyncCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke() {
        repository.syncCourses()
    }
}