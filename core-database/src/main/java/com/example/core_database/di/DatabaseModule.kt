package com.example.core_database.di

import android.app.Application
import androidx.room.Room
import com.example.core_database.dao.CoursesDao
import com.example.core_database.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "courses_db"

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoursesDao(
        appDatabase: AppDatabase
    ): CoursesDao {
        return appDatabase.coursesDao()
    }
}