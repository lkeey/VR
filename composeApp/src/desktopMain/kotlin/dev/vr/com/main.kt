package dev.vr.com

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.compose_multiplatform

fun main() = application {
    val windowState = rememberWindowState(width = 1500.dp, height = 600.dp)

    val navController = rememberNavController()

    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource(Res.drawable.compose_multiplatform),
        state = windowState,
        title = "VR",
    ) {
        App()
    }
}

