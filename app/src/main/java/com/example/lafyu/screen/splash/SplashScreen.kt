package com.example.lafyu.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lafyu.R
import com.example.lafyu.ui.theme.PrimaryBlue
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToLogin: () -> Unit
) {
    // 1. Əvvəlcə UI-ı çəkirik ki, ekran dərhal göy rəngdə açılsın
    SplashContent()

    // 2. Keçid effekti yalnız UI görünəndən sonra dinamik dinlənilir
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEffect.collectLatest { effect ->
            when (effect) {
                SplashUiEffect.NavigateToNextScreen -> onNavigateToLogin()
            }
        }
    }
}

@Composable
private fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lafyuu_logo), // Resurs adının doğruluğundan əmin ol
            contentDescription = "Lafyu Logo",
            modifier = Modifier.size(100.dp)
        )
    }
}