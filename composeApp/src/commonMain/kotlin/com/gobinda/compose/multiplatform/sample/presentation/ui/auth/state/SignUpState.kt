package com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state

import com.gobinda.compose.multiplatform.sample.utils.TripleState

data class SignUpState(
    val name: TripleState<String> = TripleState(""),
    val email: TripleState<String> = TripleState(""),
    val pass: TripleState<String> = TripleState(""),
    val cPass: TripleState<String> = TripleState(""),
    val loading: Boolean = false,
    val success: Boolean = false,
    val message : String? = null
)