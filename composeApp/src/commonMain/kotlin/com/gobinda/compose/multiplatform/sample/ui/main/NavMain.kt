package com.gobinda.compose.multiplatform.sample.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gobinda.compose.multiplatform.sample.data.RemoteKeys
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.RestDataSource
import com.gobinda.compose.multiplatform.sample.navigation.MainNav
import com.gobinda.compose.multiplatform.sample.ui.pagination.DogsScreen
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

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