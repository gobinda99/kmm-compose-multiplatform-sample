package com.gobinda.compose.multiplatform.sample.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.gobinda.compose.multiplatform.sample.component.Loading
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalResourceApi::class, KoinExperimentalAPI::class)
@Composable
internal fun SplashScreen(
    vm: SignInViewModel = koinViewModel(),
    onNavigateMain: () -> Unit,
    onNavigateLogin: () -> Unit,
) {

    LaunchedEffect(true) {
        delay(1000L)
        if (vm.anyUserLoggedIn()) {
            onNavigateMain()
        } else {
            onNavigateLogin()
        }
    }

    Loading()

}