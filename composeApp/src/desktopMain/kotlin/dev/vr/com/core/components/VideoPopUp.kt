package dev.vr.com.core.components

import DesktopVideoPlayer
import VideoPlayerScreen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.movie

@Composable
fun VideoPopUp(
    videoUrl: String,
    description: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.Black
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DesktopVideoPlayer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp),
                    videoURL = "https://streamable.com/e/0mnbp7?",
                    showControls = false,
                    autoPlay = true
                )

//
//                SwingPanel(
//                    modifier = Modifier.fillMaxSize(),
//                    factory = {
//                        val html = """
//                <html>
//                    <body style="margin:0;">
//                        <h1>hey!</h1>
//                    </body>
//                </html>
//            """.trimIndent()
//
//                        val editorPane = JEditorPane("text/html", html)
//                        editorPane.isEditable = false
//                        editorPane.contentType = "text/html"
//                        val panel = JPanel(BorderLayout())
//                        panel.add(JScrollPane(editorPane), BorderLayout.CENTER)
//                        panel
//                    }
//                )

//
//                SwingPanel(
//                    modifier = Modifier.fillMaxSize(),
//                    factory = {
//                        val panel = JPanel()
//
//                        Platform.runLater {
//                            val webView = WebView()
//                            val engine = webView.engine
//                            val html = """
//                    <html>
//                        <body style="margin:0; background-color:black;">
//                            <video width="100%" height="100%" controls autoplay>
//                                <source src="$videoUrl" type="video/mp4">
//                                Your browser does not support the video tag.
//                            </video>
//                        </body>
//                    </html>
//                """.trimIndent()
//
//                            engine.loadContent(html, "text/html")
//                            val scene = Scene(webView)
//                            val jfxPanel = JFXPanel()
//                            jfxPanel.scene = scene
//                            panel.add(jfxPanel)
//                        }
//
//                        panel
//                    }
//                )

                Text(
                    text = description,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = onDismiss) {
                    Text("Закрыть")
                }
            }
        }
    }
}
