package com.example.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_database.model.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {

    @Query("SELECT * FROM courses")
    fun observeCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE hasLike = 1")
    fun observeFavoriteCourses(): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Query("UPDATE courses SET hasLike = :hasLike WHERE id = :courseId")
    suspend fun updateLikeStatus(courseId: Int, hasLike: Boolean)
}