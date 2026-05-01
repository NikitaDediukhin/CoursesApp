package com.example.feature_main.data.repository

import com.example.core_database.dao.CoursesDao
import com.example.core_network.api.CoursesApi
import com.example.feature_main.data.mapper.toDomain
import com.example.feature_main.data.mapper.toEntity
import com.example.feature_main.domain.model.Course
import com.example.feature_main.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesApi: CoursesApi,
    private val coursesDao: CoursesDao
) : CoursesRepository {

    override fun observeCourses(): Flow<List<Course>> {
        return coursesDao.observeCourses().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun observeFavoriteCourses(): Flow<List<Course>> {
        return coursesDao.observeFavoriteCourses().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun syncCourses() {
        val response = coursesApi.getCourses()

        val localCourses = coursesDao.getCoursesOnce()
        val localLikeById = localCourses.associate { it.id to it.hasLike }

        val entities = response.courses.map { dto ->
            val entity = dto.toEntity()
            entity.copy(
                hasLike = localLikeById[entity.id] ?: entity.hasLike
            )
        }

        coursesDao.insertCourses(entities)
    }

    override suspend fun toggleFavorite(courseId: Int, hasLike: Boolean) {
        coursesDao.updateLikeStatus(courseId, hasLike)
    }
}