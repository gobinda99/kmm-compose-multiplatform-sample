package com.gobinda.compose.multiplatform.sample.ui.auth

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController

import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.SplashNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavSplashAuth(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    vm: SignInViewModel =  koinInject()

) {
    NavHost(navController = navController, startDestination = SplashNavigation.Splash.route) {
        composable(SplashNavigation.Splash.route){
            SplashScreen( onNavigateHome = {
                navController.navigate("main") {
                    popUpTo(SplashNavigation.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            }, onNavigateLogin = {
                navController.navigate(SplashNavigation.SignIn.route) {
                    popUpTo(SplashNavigation.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
        composable(SplashNavigation.SignIn.route) {
            SignInScreen(onNavigateHome = {
                navController.navigate("main") {
                    popUpTo(SplashNavigation.SignIn.route) { inclusive = true }
                    launchSingleTop = true
                }
            }, onNavigateSignUp = {
                navController.navigate(SplashNavigation.SignUp.route) {
                    launchSingleTop = true
                }
            })
        }
        composable(SplashNavigation.SignUp.route) {
            SignUpScreen {
                navController.navigate(SplashNavigation.SignIn.route) {
                    popUpTo(SplashNavigation.SignUp.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }


    }

}