package com.gobinda.compose.multiplatform.sample.presentation.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.presentation.navigation.MainNav
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.PagingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMain(
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNav.Home.route) {

        composable(MainNav.Home.route) {
            PagingScreen()
        }

    }

}