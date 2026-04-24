package com.example.feature_main.di

import androidx.lifecycle.ViewModel

class MainComponentViewModel : ViewModel() {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.factory().create(MainDepsProvider.deps)
    }
}