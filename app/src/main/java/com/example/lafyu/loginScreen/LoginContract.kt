package com.example.lafyu.loginScreen

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val generalError: String? = null
)

sealed interface LoginUiEvent {
    data class OnEmailChanged(val email: String) : LoginUiEvent
    data class OnPasswordChanged(val password: String) : LoginUiEvent
    data object OnSignInClicked : LoginUiEvent
    data object OnGoogleSignInClicked : LoginUiEvent
    data object OnFacebookSignInClicked : LoginUiEvent
    data object OnForgotPasswordClicked : LoginUiEvent
    data object OnRegisterClicked : LoginUiEvent
}

sealed interface LoginUiEffect {
    data object NavigateToHome : LoginUiEffect
    data object NavigateToRegister : LoginUiEffect
    data object NavigateToForgotPassword : LoginUiEffect
}