package com.gobinda.compose.multiplatform.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.AppNavigation
import com.gobinda.compose.multiplatform.sample.ui.auth.NavSplashAuth
import com.gobinda.compose.multiplatform.sample.ui.main.NavMain

@Composable
fun NavApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = AppNavigation.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = AppNavigation.Splash.route) {
            NavSplashAuth()
        }
        composable(route = AppNavigation.Main.route) {
            NavMain()
        }
    }

}