package com.gobinda.compose.multiplatform.sample

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.di.appModule
import sample.composeapp.generated.resources.*
import com.gobinda.compose.multiplatform.sample.theme.AppTheme
import com.gobinda.compose.multiplatform.sample.theme.LocalThemeIsDark
import com.gobinda.compose.multiplatform.sample.ui.NavGraph
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinApplication

@Composable
internal fun App(context: Context) = KoinApplication(application = {
    modules(appModule(context))
}) {
    /* AppTheme {
         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .windowInsetsPadding(WindowInsets.safeDrawing)
                 .padding(16.dp),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Text(
                 text = stringResource(Res.string.cyclone),
                 fontFamily = FontFamily(Font(Res.font.IndieFlower_Regular)),
                 style = MaterialTheme.typography.displayLarge
             )

             var isAnimate by remember { mutableStateOf(false) }
             val transition = rememberInfiniteTransition()
             val rotate by transition.animateFloat(
                 initialValue = 0f,
                 targetValue = 360f,
                 animationSpec = infiniteRepeatable(
                     animation = tween(1000, easing = LinearEasing)
                 )
             )

             Image(
                 modifier = Modifier
                     .size(250.dp)
                     .padding(16.dp)
                     .run { if (isAnimate) rotate(rotate) else this },
                 imageVector = vectorResource(Res.drawable.ic_cyclone),
                 colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                 contentDescription = null
             )

             ElevatedButton(
                 modifier = Modifier
                     .padding(horizontal = 8.dp, vertical = 4.dp)
                     .widthIn(min = 200.dp),
                 onClick = { isAnimate = !isAnimate },
                 content = {
                     Icon(vectorResource(Res.drawable.ic_rotate_right), contentDescription = null)
                     Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                     Text(
                         stringResource(if (isAnimate) Res.string.stop else Res.string.run)
                     )
                 }
             )

             var isDark by LocalThemeIsDark.current
             val icon = remember(isDark) {
                 if (isDark) Res.drawable.ic_light_mode
                 else Res.drawable.ic_dark_mode
             }

             ElevatedButton(
                 modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
                 onClick = { isDark = !isDark },
                 content = {
                     Icon(vectorResource(icon), contentDescription = null)
                     Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                     Text(stringResource(Res.string.theme))
                 }
             )

             val uriHandler = LocalUriHandler.current
             TextButton(
                 modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).widthIn(min = 200.dp),
                 onClick = { uriHandler.openUri("https://github.com/terrakok") },
             ) {
                 Text(stringResource(Res.string.open_github))
             }
         }
     }*/

    println("hello 1234 e")

    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            println("hello 1234 f")

            NavGraph()
        }
    }


}
