package com.gobinda.compose.multiplatform.sample.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.navigation.MainNav
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMain(
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNav.Home.route) {
        composable(MainNav.Home.route) {
            val scope = rememberCoroutineScope()
             val client = koinInject<HttpClient>()


            LaunchedEffect(true) {
                scope.launch {
                    runCatching {
                       client.get("https://ktor.io/docs/")
                    }.onSuccess {

                    }.onFailure {
                        Napier.e(it.message?: "Error", it.cause)
                    }
                }
            }
        }

    }

}