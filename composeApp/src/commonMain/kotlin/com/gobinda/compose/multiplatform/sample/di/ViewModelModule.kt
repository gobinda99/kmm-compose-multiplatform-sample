package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.ui.auth.SignUpViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val vModelModule = module {

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)
}