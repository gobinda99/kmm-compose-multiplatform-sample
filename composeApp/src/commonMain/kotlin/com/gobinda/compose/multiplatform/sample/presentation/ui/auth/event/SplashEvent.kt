package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event

sealed interface SplashEvent {
    data object LogIn : SplashEvent
}