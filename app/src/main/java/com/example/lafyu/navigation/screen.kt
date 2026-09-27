package com.example.lafyu.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")
    data object Login : Screen("login_screen")
    // Növbəti ekranları da bura əlavə edəcəyik
}