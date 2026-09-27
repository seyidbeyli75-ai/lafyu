package com.example.lafyu.screen.splash

sealed interface SplashUiEffect {
    data object NavigateToNextScreen : SplashUiEffect
}