package com.example.feature_main.di

import com.example.feature_main.data.repository.CoursesRepositoryImpl
import com.example.feature_main.domain.repository.CoursesRepository
import dagger.Binds
import dagger.Module

@Module
interface MainDataModule {

    @Binds
    fun bindCoursesRepository(
        impl: CoursesRepositoryImpl
    ): CoursesRepository
}