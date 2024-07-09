package com.gobinda.compose.multiplatform.sample.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.MainNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMain(
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNav.Home.route) {
        composable(MainNav.Home.route) {

        }

    }

}