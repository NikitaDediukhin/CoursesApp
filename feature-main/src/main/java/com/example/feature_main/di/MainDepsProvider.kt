package com.example.feature_main.di

interface MainDepsProvider {

    val deps: MainDependencies

    companion object : MainDepsProvider by MainDepsStore
}