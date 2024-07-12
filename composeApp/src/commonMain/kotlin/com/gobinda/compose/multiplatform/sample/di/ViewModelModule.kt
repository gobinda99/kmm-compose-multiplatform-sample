package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.ui.auth.SignUpViewModel
import com.gobinda.compose.multiplatform.sample.ui.auth.SplashViewModel
import com.gobinda.compose.multiplatform.sample.ui.pagination.DogsViewModeMediator
import com.gobinda.compose.multiplatform.sample.ui.pagination.DogsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val vModelModule = module {

    viewModelOf(::SplashViewModel)

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)

    viewModelOf(::DogsViewModel)

    viewModelOf(::DogsViewModeMediator)

}