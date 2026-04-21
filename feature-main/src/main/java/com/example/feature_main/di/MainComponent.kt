package com.example.feature_main.di

import com.example.feature_main.presentation.MainFragment
import dagger.Component

@MainFeatureScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainDataModule::class]
)
interface MainComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: MainDependencies
        ): MainComponent
    }
}