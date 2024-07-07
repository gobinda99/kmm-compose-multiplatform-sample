package com.gobinda.compose.multiplatform.sample.utils

import org.jetbrains.compose.resources.StringResource
import sample.composeapp.generated.resources.Res
import sample.composeapp.generated.resources.error

data class TripleState<T>(var value : T, var isError: Boolean = false, var message : StringResource = Res.string.error)