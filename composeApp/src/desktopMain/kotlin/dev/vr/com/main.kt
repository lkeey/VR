package dev.vr.com

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vr.com.core.theme.VRTheme
import javax.imageio.ImageIO


fun main() = application {

    val LOGO_NAME = "logo.png"

    if (System.getProperty("os.name").lowercase().contains("mac")) {
        val iconStream = object {}.javaClass.getResourceAsStream("/$LOGO_NAME")
        val img = ImageIO.read(iconStream)
        java.awt.Taskbar.getTaskbar().iconImage = img
    }

    val windowState = rememberWindowState(
        width = 1500.dp, height = 1000.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        icon = painterResource("/$LOGO_NAME"),
        state = windowState,
        title = "VR Arena",
    ) {
        VRTheme {
            App()
        }
    }
}
