package com.example.lafyu.screen.splash

sealed interface SplashUiEvent {
    data object OnSplashStarted : SplashUiEvent
}