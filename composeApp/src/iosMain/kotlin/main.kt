import androidx.compose.ui.window.ComposeUIViewController
import com.gobinda.compose.multiplatform.sample.App
import com.gobinda.compose.multiplatform.sample.common.Context
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App(Context()) }
