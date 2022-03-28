package com.example.datastoresample.screens

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
}
