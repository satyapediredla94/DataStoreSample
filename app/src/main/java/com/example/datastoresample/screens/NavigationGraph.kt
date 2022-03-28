package com.example.datastoresample

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.datastoresample.screens.HomeScreen
import com.example.datastoresample.screens.Screen
import com.example.datastoresample.screens.WelcomeScreen

@Composable
fun ScreenSetup(
    navController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}