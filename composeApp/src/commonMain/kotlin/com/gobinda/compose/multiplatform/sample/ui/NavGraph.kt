package com.gobinda.compose.multiplatform.sample.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDatabase
import com.gobinda.compose.multiplatform.sample.ui.auth.SignInScreen
import com.gobinda.compose.multiplatform.sample.ui.auth.SignUpScreen
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    vm: SignInViewModel =  koinInject()

) {
    NavHost(navController = navController, startDestination = if(runBlocking { vm.anyUserLoggedIn() } ) "home" else "sign_in") {
        composable("sign_in") {
            SignInScreen(onNavigateHome = {
                navController.navigate("home") {
                    popUpTo("sign_in") { inclusive = true }
                    launchSingleTop = true
                }
            }, onNavigateSignUp = {
                navController.navigate("signup") {
                    launchSingleTop = true
                }
            })
        }
        composable("signup") {
            SignUpScreen {
                navController.navigate("sign_in") {
                    popUpTo("signup") { inclusive = false }
                    launchSingleTop = true
                }
            }
        }
        composable("home") {
             Text("Hello World")

           /* NavDrawerScreen(drawerState, onItemClick = {
                coroutineScope.launch {
                    when(it){
                        "logout" -> {
                            vm.logout()
                            navController.navigate("sign_in") {
                                popUpTo("signup") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                        else -> {
                            delay(500)
                            drawerState.close()
                        }
                    }
                }
            }) {
                HomeListScreen(onOpenDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }, onItemClick = {
                    navController.navigate("details")

                }, onButtonItemClick = {
                    navController.navigate("tab_pager")
                })
            }*/
        }



    }

}