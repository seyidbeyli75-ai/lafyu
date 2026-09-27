package com.example.lafyu.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _uiEffect = Channel<LoginUiEffect>()
    val uiEffect = _uiEffect.receiveAsFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChanged -> {
                _uiState.update { it.copy(email = event.email, generalError = null) }
            }
            is LoginUiEvent.OnPasswordChanged -> {
                _uiState.update { it.copy(password = event.password, generalError = null) }
            }
            LoginUiEvent.OnSignInClicked -> performLogin()
            LoginUiEvent.OnRegisterClicked -> {
                viewModelScope.launch { _uiEffect.send(LoginUiEffect.NavigateToRegister) }
            }
            LoginUiEvent.OnForgotPasswordClicked -> {
                viewModelScope.launch { _uiEffect.send(LoginUiEffect.NavigateToForgotPassword) }
            }
            LoginUiEvent.OnGoogleSignInClicked -> { /* Google Auth */ }
            LoginUiEvent.OnFacebookSignInClicked -> { /* Facebook Auth */ }
        }
    }

    private fun performLogin() {
        val currentState = _uiState.value

        if (currentState.email.isBlank() || currentState.password.isBlank()) {
            _uiState.update {
                it.copy(generalError = "Oops! Your Email or Password is Not Correct")
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            if (currentState.email == "derlaxy@yahoo.com" && currentState.password == "123456") {
                _uiEffect.send(LoginUiEffect.NavigateToHome)
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        generalError = "Oops! Your Email or Password is Not Correct"
                    )
                }
            }
        }
    }
}