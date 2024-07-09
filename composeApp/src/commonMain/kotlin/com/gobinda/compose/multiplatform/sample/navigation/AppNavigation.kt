package com.gobinda.compose.multiplatform.sample.navigation

import androidx.navigation.NamedNavArgument

sealed class AppNavigation(
    val route: String, val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash : AppNavigation(route = "splash", arguments = emptyList())

    data object Main : AppNavigation(route = "main", arguments = emptyList())

}
