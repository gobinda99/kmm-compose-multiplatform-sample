package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state

import com.gobinda.compose.multiplatform.sample.utils.TripleState
import org.jetbrains.compose.resources.StringResource

data class SignInState(
    val email: TripleState<String> = TripleState(""),
    val pass: TripleState<String> = TripleState(""),
    val loading: Boolean = false,
    val success: Boolean = false,
    val message: StringResource? = null,
)