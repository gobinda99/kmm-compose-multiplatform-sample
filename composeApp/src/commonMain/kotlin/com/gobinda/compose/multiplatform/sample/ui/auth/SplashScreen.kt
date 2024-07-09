package com.gobinda.compose.multiplatform.sample.ui.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.gobinda.compose.multiplatform.sample.component.Loading
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashScreen(
    vm: SignInViewModel = koinInject(),
    onNavigateHome: () -> Unit,
    onNavigateLogin: () -> Unit,
) {

    LaunchedEffect(true) {
        delay(1000L)
        if (vm.anyUserLoggedIn()) {
            onNavigateHome()
        } else {
            onNavigateLogin()
        }
    }

    Loading()

}