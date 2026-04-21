package com.example.feature_main.di

import com.example.core_database.dao.CoursesDao
import com.example.core_network.api.CoursesApi

interface MainDependencies {
    fun coursesApi(): CoursesApi
    fun coursesDao(): CoursesDao
}