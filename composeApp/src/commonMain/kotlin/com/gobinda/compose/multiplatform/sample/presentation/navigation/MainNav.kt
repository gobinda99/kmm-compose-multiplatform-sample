package com.gobinda.compose.multiplatform.sample.presentation.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import sample.composeapp.generated.resources.Res
import sample.composeapp.generated.resources.ic_cyclone

@OptIn(ExperimentalResourceApi::class)
sealed class MainNav (
    val route: String,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {

   data object Home : MainNav(
        route = "home", title = "Home",
        selectedIcon = Res.drawable.ic_cyclone,
        unSelectedIcon = Res.drawable.ic_cyclone
    )




}

