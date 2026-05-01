package com.example.feature_main.di

import com.example.feature_main.presentation.favourites.FavoritesFragment
import com.example.feature_main.presentation.main.MainFragment
import dagger.Component

@MainFeatureScope
@Component(
    dependencies = [MainDependencies::class],
    modules = [MainDataModule::class]
)
interface MainComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: FavoritesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: MainDependencies
        ): MainComponent
    }
}