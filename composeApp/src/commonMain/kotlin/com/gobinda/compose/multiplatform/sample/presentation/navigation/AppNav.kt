package com.gobinda.compose.multiplatform.sample.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class AppNav(
    val route: String, val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash : AppNav(route = "splash", arguments = emptyList())

    data object Main : AppNav(route = "main", arguments = emptyList())

}
