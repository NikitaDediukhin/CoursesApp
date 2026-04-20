package com.example.feature_main.data.mapper

import com.example.core_database.model.CourseEntity
import com.example.core_network.dto.CourseDto
import com.example.feature_main.domain.model.Course

fun CourseDto.toEntity(): CourseEntity {
    return CourseEntity(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}

fun CourseEntity.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}