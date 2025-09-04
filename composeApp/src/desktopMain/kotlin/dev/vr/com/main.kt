package dev.vr.com

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import dev.vr.com.core.VRTheme
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.compose_multiplatform
import vr.composeapp.generated.resources.logo_vs_arena


fun main() = application {
    val windowState = rememberWindowState(
        width = 1500.dp, height = 600.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource(Res.drawable.logo_vs_arena),
        state = windowState,
        title = "VR",
    ) {
        VRTheme {
            App()
        }
    }
}
