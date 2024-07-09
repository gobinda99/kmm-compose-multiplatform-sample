package com.gobinda.compose.multiplatform.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.di.appModule
import com.gobinda.compose.multiplatform.sample.theme.AppTheme
import org.koin.compose.KoinApplication

@Composable
internal fun App(context: Context) = KoinApplication(application = {
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

}
