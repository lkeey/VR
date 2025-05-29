package dev.vr.com.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.vr.com.core.Theme
import vr.composeapp.generated.resources.PressStart
import vr.composeapp.generated.resources.Res

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
//                Player(
//                    url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
//                    onFinish = {},
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(400.dp),
//                    isResumed = true,
//                    volume = 1f,
//                    speed = 1f,
//                    seek = 0f,
//                    isFullscreen = false,
//                    progressState = mutableStateOf( Progress(0f, 0))
//                )

                Text(
                    text = description,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.PressStart)),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                RoundedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = "ЗАКРЫТЬ",
                    color = Theme.colors.pinkAction,
                    onClick = onDismiss
                )
            }
        }
    }
}
