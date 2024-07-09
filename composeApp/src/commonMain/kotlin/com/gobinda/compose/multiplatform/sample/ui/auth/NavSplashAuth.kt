package com.gobinda.compose.multiplatform.sample.ui.auth

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.SplashNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavSplashAuth(
    onNavigateMain: () -> Unit
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashNav.Splash.route) {
        composable(SplashNav.Splash.route) {
            SplashScreen(onNavigateMain = onNavigateMain, onNavigateLogin = {
                navController.navigate(SplashNav.SignIn.route) {
                    popUpTo(SplashNav.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
        composable(SplashNav.SignIn.route) {
            SignInScreen(onNavigateMain = onNavigateMain, onNavigateSignUp = {
                navController.navigate(SplashNav.SignUp.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(SplashNav.SignUp.route) {
            SignUpScreen {
                navController.navigate(SplashNav.SignIn.route) {
                    popUpTo(SplashNav.SignUp.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }


    }

}