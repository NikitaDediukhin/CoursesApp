package com.example.coursesapp

import android.app.Application
import com.example.coursesapp.di.AppComponent
import com.example.coursesapp.di.DaggerAppComponent
import com.example.feature_main.di.MainDepsStore

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        MainDepsStore.deps = appComponent
    }
}