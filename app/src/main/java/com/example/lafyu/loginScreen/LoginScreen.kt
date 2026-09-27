package com.example.lafyu.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lafyu.R
import com.example.lafyu.ui.theme.components.LafyuuButton
import com.example.lafyu.ui.theme.components.LafyuuTextField
import com.example.lafyu.ui.theme.NeutralGrey
import com.example.lafyu.ui.theme.PrimaryBlue
import com.example.lafyu.ui.theme.components.SocialLoginButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                LoginUiEffect.NavigateToHome -> onNavigateToHome()
                LoginUiEffect.NavigateToRegister -> onNavigateToRegister()
                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // <--- ARKA PLAN BEYAZ YAPILDI
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.lafyuu_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(72.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome to Lafyuu",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF223263)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign in to continue",
            fontSize = 12.sp,
            color = NeutralGrey
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Email
        LafyuuTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginUiEvent.OnEmailChanged(it)) },
            hint = "Your Email",
            leadingIcon = Icons.Outlined.Email,
            isError = state.generalError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password
        LafyuuTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginUiEvent.OnPasswordChanged(it)) },
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isError = state.generalError != null,
            errorMessage = state.generalError,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sign In Button
        LafyuuButton(
            text = "Sign In",
            onClick = { viewModel.onEvent(LoginUiEvent.OnSignInClicked) }
        )

        Spacer(modifier = Modifier.height(21.dp))

        Text(
            text = "OR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = NeutralGrey
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Google
        SocialLoginButton(
            text = "Login with Google",
            iconRes = R.drawable.google_photo,
            onClick = { viewModel.onEvent(LoginUiEvent.OnGoogleSignInClicked) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Facebook
        SocialLoginButton(
            text = "Login with facebook",
            iconRes = R.drawable.facebook,
            onClick = { viewModel.onEvent(LoginUiEvent.OnFacebookSignInClicked) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Forgot Password?",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue,
            modifier = Modifier.clickable { viewModel.onEvent(LoginUiEvent.OnForgotPasswordClicked) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "Don't have an account? ", fontSize = 12.sp, color = NeutralGrey)
            Text(
                text = "Register",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue,
                modifier = Modifier.clickable { viewModel.onEvent(LoginUiEvent.OnRegisterClicked) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}