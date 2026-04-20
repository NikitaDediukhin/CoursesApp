package com.example.coursesapp.di

import android.app.Application
import com.example.core_database.di.DatabaseModule
import com.example.core_network.di.NetworkModule
import com.example.feature_main.di.MainDataModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        MainDataModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}