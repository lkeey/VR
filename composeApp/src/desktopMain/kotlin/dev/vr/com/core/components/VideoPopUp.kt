package dev.vr.com.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.vr.com.core.Theme

@Composable
fun VideoPopUp(
    text: String,
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
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 10.sp,
//                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.PressStart)),
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))

                Player(
                    url = videoUrl,
                    onFinish = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    isResumed = true,
                    volume = 1f,
                    speed = 1f,
                    seek = 0f,
                    isFullscreen = false,
                    progressState = mutableStateOf( Progress(0f, 0))
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = description,
                    color = Color.White,
                    fontSize = 10.sp,
//                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.PressStart)),
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
