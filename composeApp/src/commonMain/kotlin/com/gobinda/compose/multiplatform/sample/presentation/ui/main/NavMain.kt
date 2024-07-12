package com.gobinda.compose.multiplatform.sample.presentation.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.presentation.navigation.MainNav
import com.gobinda.compose.multiplatform.sample.presentation.ui.pagination.DogsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavMain(
) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = MainNav.Home.route) {
       /* composable(MainNav.Home.route) {
            val scope = rememberCoroutineScope()
            val data = koinInject<AppDatabase>()
            val rest = koinInject<RestDataSource>()


            LaunchedEffect(true) {
                scope.launch {

                    runCatching {
                        withContext(Dispatchers.IO){
                            data.remoteKeyDao().insertRemote(listOf(
                                RemoteKeys("1",1,2),
                                RemoteKeys("c",1,2),
                                RemoteKeys("c",1,2),
                                RemoteKeys("c",1,2),
                            ))
                        }

                        rest.getAllDogs(1,20)

                    }.onSuccess {
                        Napier.i(message = it?.size.toString() ?: "", tag = "GGGYYY")
                    }.onFailure {
                        Napier.e(message = it.message ?: "", throwable = it.cause, tag = "GGGYYY")
                    }
                }
            }
        }*/

        composable(MainNav.Home.route) {
            DogsScreen()
        }

    }

}