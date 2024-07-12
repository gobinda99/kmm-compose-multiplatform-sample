package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event

sealed interface SignUpEvent{
    data object  Validate : SignUpEvent
    class Name(val name: String) : SignUpEvent
    class Email(val name: String) : SignUpEvent
    class Pass( val name: String) : SignUpEvent
    class Confirm(val name: String) : SignUpEvent
}