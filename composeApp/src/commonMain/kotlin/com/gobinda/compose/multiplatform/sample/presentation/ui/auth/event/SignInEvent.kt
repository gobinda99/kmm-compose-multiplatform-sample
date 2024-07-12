package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event

sealed interface SignInEvent{
    data object  Validate : SignInEvent
    class Email(val name: String) : SignInEvent
    class Pass( val name: String) : SignInEvent
}