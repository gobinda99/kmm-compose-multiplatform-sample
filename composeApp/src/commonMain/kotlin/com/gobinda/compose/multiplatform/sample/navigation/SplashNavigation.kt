package com.gobinda.compose.multiplatform.sample.navigation

import androidx.navigation.NamedNavArgument

sealed class SplashNavigation(
    val route: String, val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash : SplashNavigation(route = "splash")

    data object SignIn : SplashNavigation(route = "sign_in")

    data object SignUp : SplashNavigation(route = "signup",)


}

