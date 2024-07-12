package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state

sealed interface SplashState {
    data object Loading : SplashState
    data class Success(val anyUserLogIn: Boolean) : SplashState
    data class Error(val message: String) : SplashState
}