package com.example.feature_main.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_main.presentation.favourites.FavoritesViewModel
import javax.inject.Inject
import javax.inject.Provider

class MainViewModelFactory @Inject constructor(
    private val mainViewModelProvider: Provider<MainViewModel>,
    private val favoritesViewModelProvider: Provider<FavoritesViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> mainViewModelProvider.get() as T
            FavoritesViewModel::class.java -> favoritesViewModelProvider.get() as T
            else -> error("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}