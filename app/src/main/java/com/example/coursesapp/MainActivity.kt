package com.example.coursesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.feature_main.presentation.MainFragment
import com.exapmple.feature_login.presentation.LoginFragment

class MainActivity : AppCompatActivity(), LoginFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment.newInstance())
                .commit()
        }
    }

    override fun onLoginSuccess() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}