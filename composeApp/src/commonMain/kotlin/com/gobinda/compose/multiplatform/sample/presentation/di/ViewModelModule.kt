package com.gobinda.compose.multiplatform.sample.presentation.di

import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SignUpViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SplashViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.DogsMediatorViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.DogsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val vModelModule = module {

    viewModelOf(::SplashViewModel)

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)

    viewModelOf(::DogsViewModel)

    viewModelOf(::DogsMediatorViewModel)

}