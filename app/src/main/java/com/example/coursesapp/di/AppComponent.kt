package com.example.coursesapp.di

import android.app.Application
import com.example.core_database.dao.CoursesDao
import com.example.core_database.di.DatabaseModule
import com.example.core_network.api.CoursesApi
import com.example.core_network.di.NetworkModule
import com.example.feature_main.di.MainDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : MainDependencies {

    override fun coursesApi(): CoursesApi

    override fun coursesDao(): CoursesDao

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}