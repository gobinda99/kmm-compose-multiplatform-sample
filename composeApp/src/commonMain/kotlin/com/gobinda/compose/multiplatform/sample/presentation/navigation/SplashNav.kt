package com.gobinda.compose.multiplatform.sample.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class SplashNav(
    val route: String, val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash : SplashNav(route = "splash")

    data object SignIn : SplashNav(route = "sign_in")

    data object SignUp : SplashNav(route = "signup",)


}

