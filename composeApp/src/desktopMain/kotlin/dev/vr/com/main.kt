package dev.vr.com

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.vr.com.core.theme.VRTheme
import javax.imageio.ImageIO

const val LOGO_NAME = "logo.png"

@Suppress("DEPRECATION")
fun main() = application {

    // Явно указываем JNA и VLC плагины
    System.setProperty("jna.library.path", "/Applications/VLC.app/Contents/MacOS/lib")
    System.setProperty("VLC_PLUGIN_PATH", "/Applications/VLC.app/Contents/MacOS/plugins")

    // Для отладки — чтобы видеть, что vlcj подхватил именно arm64 libvlc
    System.setProperty("vlcj.log", "DEBUG")


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
