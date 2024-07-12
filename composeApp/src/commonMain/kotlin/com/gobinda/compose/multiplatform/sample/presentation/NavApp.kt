package com.gobinda.compose.multiplatform.sample.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.presentation.navigation.AppNav
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.NavSplashAuth
import com.gobinda.compose.multiplatform.sample.presentation.ui.main.NavMain

@Composable
fun NavApp(
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppNav.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = AppNav.Splash.route) {
            NavSplashAuth(onNavigateMain = {
                navController.navigate(AppNav.Main.route) {
                    popUpTo(AppNav.Splash.route) {inclusive = true}
                }
            })
        }
        composable(route = AppNav.Main.route) {
            NavMain()
        }
    }

}