package com.gobinda.compose.multiplatform.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.di.AppModule
import com.gobinda.compose.multiplatform.sample.di.appModule
import com.gobinda.compose.multiplatform.sample.presentation.NavApp
import com.gobinda.compose.multiplatform.sample.theme.AppTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import okio.FileSystem
import org.koin.compose.KoinContext
import org.koin.dsl.koinApplication



@Composable
internal fun App(context: Context) {
    KoinContext(context = koinApplication {
        Napier.base(DebugAntilog())
        modules(appModule(context))
    }.koin) {
        AppTheme {
            setSingletonImageLoaderFactory { context ->
                getAsyncImageLoader(context)
            }
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


fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}




