package com.example.feature_main.domain.repository

import com.example.feature_main.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun observeCourses(): Flow<List<Course>>
    fun observeFavoriteCourses(): Flow<List<Course>>
    suspend fun syncCourses()
    suspend fun toggleFavorite(courseId: Int, hasLike: Boolean)
}