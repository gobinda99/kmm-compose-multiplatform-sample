package com.gobinda.compose.multiplatform.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.di.appModule
import com.gobinda.compose.multiplatform.sample.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.compose.KoinContext
import org.koin.dsl.koinApplication

@Composable
internal fun App(context: Context) {
    KoinContext(context = koinApplication {
        Napier.base(DebugAntilog())
        modules(appModule(context))
    }.koin) {
        AppTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavApp()
            }
        }
    }
}

/*@Composable
internal fun App(context: Context) = KoinApplication(application = {
    Napier.base(DebugAntilog())
    modules(appModule(context))
}) {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavApp()
        }
    }

}*/




