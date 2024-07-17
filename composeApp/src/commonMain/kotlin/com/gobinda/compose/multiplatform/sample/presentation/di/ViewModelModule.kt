package com.gobinda.compose.multiplatform.sample.presentation.di

import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SignInViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SignUpViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.SplashViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.PagingMediatorViewModel
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.PagingViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val vModelModule = module {

    viewModelOf(::SplashViewModel)

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)

    viewModelOf(::PagingViewModel)

    viewModelOf(::PagingMediatorViewModel)

}