package com.example.feature_main.domain.usecase

import com.example.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(courseId: Int, hasLike: Boolean) {
        repository.toggleFavorite(courseId, hasLike)
    }
}