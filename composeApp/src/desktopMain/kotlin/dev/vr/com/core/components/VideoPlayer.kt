package dev.vr.com.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.Component
import java.nio.file.Files
import java.util.Locale

@Composable
fun VideoPlayer(
    url: String,
    modifier: Modifier,
) {
    val url = remember(url) {
        extractResourceToTempFile(url, "video_from_res")
    }

    val mediaPlayerComponent = remember { initializeMediaPlayerComponent() }
    val mediaPlayer = remember { mediaPlayerComponent.mediaPlayer() }

    val factory = remember { { mediaPlayerComponent } }

    LaunchedEffect(url) {
        mediaPlayer.media().start(url)
    }
    DisposableEffect(Unit) {
        onDispose(mediaPlayer::release)
    }
    SwingPanel(
        factory = factory,
        background = Color.Transparent,
        modifier = modifier,
        update = {

        }
    )
}

private fun initializeMediaPlayerComponent(): Component {
    NativeDiscovery().discover()
    return if (isMacOS()) {
        CallbackMediaPlayerComponent()
    } else {
        EmbeddedMediaPlayerComponent()
    }
}

private fun Component.mediaPlayer() = when (this) {
    is CallbackMediaPlayerComponent -> mediaPlayer()
    is EmbeddedMediaPlayerComponent -> mediaPlayer()
    else -> error("mediaPlayer() can only be called on vlcj player components")
}

private fun isMacOS(): Boolean {
    val os = System
        .getProperty("os.name", "generic")
        .lowercase(Locale.ENGLISH)
    return "mac" in os || "darwin" in os
}

fun extractResourceToTempFile(resourcePath: String, tempFileNamePrefix: String): String {
    val tempFile = Files.createTempFile(tempFileNamePrefix, ".mp4").toFile()
    tempFile.deleteOnExit()

    val inputStream = {}.javaClass.getResourceAsStream(resourcePath)
        ?: error("Resource not found: $resourcePath")

    inputStream.use { input ->
        tempFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    return tempFile.toURI().toString() // file:///... — для VLC
}