package com.example.lafyu.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lafyu.loginScreen.LoginScreen
import com.example.lafyu.loginScreen.LoginViewModel
import com.example.lafyu.screen.splash.SplashScreen
import com.example.lafyu.screen.splash.SplashViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // Splash Screen
        composable(route = Screen.Splash.route) {
            val viewModel: SplashViewModel = viewModel()
            SplashScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Login Screen
        composable(route = Screen.Login.route) {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToHome = {
                    // Hələlik Home ekranı olmadığı üçün boş saxlayırıq
                },
                onNavigateToRegister = {
                    // Növbəti addımda Register keçidini yazacağıq
                }
            )
        }
    }
}