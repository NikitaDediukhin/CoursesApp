package com.example.coursesapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coursesapp.R
import com.example.coursesapp.presentation.navigation.AppNavigator
import com.example.feature_login.presentation.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), LoginFragment.Listener {

    private val navigator by lazy {
        AppNavigator(supportFragmentManager)
    }

    private val bottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupWindowInsets()
        setupBottomNavigation()

        if (savedInstanceState == null) {
            showBottomNavigation(false)
            navigator.openLogin()
        }
    }

    override fun onLoginSuccess() {
        showBottomNavigation(true)
        bottomNavigationView.selectedItemId = R.id.menu_main
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, systemBars.top, 0, 0)
            insets
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main -> {
                    navigator.openMain()
                    true
                }

                R.id.menu_favorites -> {
                    navigator.openFavorites()
                    true
                }

                R.id.menu_account -> {
                    navigator.openAccount()
                    true
                }

                else -> false
            }
        }
    }

    private fun showBottomNavigation(isVisible: Boolean) {
        bottomNavigationView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}