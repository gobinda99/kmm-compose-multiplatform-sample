package com.gobinda.compose.multiplatform.sample.presentation.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.gobinda.compose.multiplatform.sample.common.collectAsStateMultiplatform
import com.gobinda.compose.multiplatform.sample.presentation.component.Loading
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event.SplashEvent
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state.SplashState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalResourceApi::class, KoinExperimentalAPI::class)
@Composable
internal fun SplashScreen(
    vm: SplashViewModel = koinViewModel(),
    onNavigateMain: () -> Unit,
    onNavigateLogin: () -> Unit,
) {
    val uiState by vm.state.collectAsStateMultiplatform()

    LaunchedEffect(true){
        vm.userIntent.trySend(SplashEvent.LogIn)
    }

    when (val state = uiState) {
        is SplashState.Loading -> Loading()
        is SplashState.Success -> {
            if (state.logIn) {
                onNavigateMain()
            } else {
//                 onNavigateMain()
                onNavigateLogin()
            }
        }
        is SplashState.Error -> {}
    }

}