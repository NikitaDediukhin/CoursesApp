package com.example.coursesapp.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.coursesapp.R
import com.example.coursesapp.presentation.AccountFragment
import com.example.feature_login.presentation.LoginFragment
import com.example.feature_main.presentation.favourites.FavoritesFragment
import com.example.feature_main.presentation.main.MainFragment

class AppNavigator(
    private val fragmentManager: FragmentManager
) {

    fun openLogin() {
        replace(LoginFragment.newInstance())
    }

    fun openMain() {
        replace(MainFragment.newInstance())
    }

    fun openFavorites() {
        replace(FavoritesFragment.newInstance())
    }

    fun openAccount() {
        replace(AccountFragment.newInstance())
    }

    private fun replace(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}