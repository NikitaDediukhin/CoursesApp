package com.example.coursesapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coursesapp.R
import com.example.feature_main.presentation.FavoritesFragment
import com.example.feature_main.presentation.MainFragment
import com.example.feature_login.presentation.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), LoginFragment.Listener {

    private val bottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                0,
                systemBars.top,
                0,
                0
            )

            insets
        }

        setupBottomNavigation()

        if (savedInstanceState == null) {
            bottomNavigationView.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment.newInstance())
                .commit()
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main -> {
                    openMain()
                    true
                }

                R.id.menu_favorites -> {
                    openFavorites()
                    true
                }

                R.id.menu_account -> {
                    openAccount()
                    true
                }

                else -> false
            }
        }
    }

    override fun onLoginSuccess() {
        bottomNavigationView.visibility = View.VISIBLE
        bottomNavigationView.selectedItemId = R.id.menu_main
        openMain()
    }

    private fun openMain() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .commit()
    }

    private fun openFavorites() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FavoritesFragment.newInstance())
            .commit()
    }

    private fun openAccount() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, AccountFragment.newInstance())
            .commit()
    }
}