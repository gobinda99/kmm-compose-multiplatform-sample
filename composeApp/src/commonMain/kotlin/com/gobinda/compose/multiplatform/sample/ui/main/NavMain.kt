package com.gobinda.compose.multiplatform.sample.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.gobinda.compose.multiplatform.sample.ui.auth.SignInViewModel

import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.MainNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMain(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    vm: SignInViewModel =  koinInject()

) {
    NavHost(navController = navController, startDestination = MainNavigation.Home.route) {
        composable(MainNavigation.Home.route) {
           Text("HOME")
        }
        composable("main") {

        }


    }

}