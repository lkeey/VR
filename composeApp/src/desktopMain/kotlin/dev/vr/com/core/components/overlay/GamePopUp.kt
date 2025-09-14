package dev.vr.com.core.components.overlay

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.components.movie.VideoPlayer
import dev.vr.com.core.components.movie.rememberVideoPlayerState
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.Font
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Light
import vr.composeapp.generated.resources.Res

/**
 * good project:
 * https://github.com/realityexpander/CloudCoverUSA2/blob/37feae3c18c55591331c2981eb149b2ab3e84dd9/composeApp/build.gradle.kts
 */

@Composable
fun GamePopUp(
    gameModel: GameModel,
    onDismiss: () -> Unit
) {

    var isShowingMovie by remember {
        mutableStateOf(false)
    }

    val state = rememberVideoPlayerState()

    /**
     * file:///полный_путь
     * file:///Users/lkey/Desktop/videos/информатика.mp4
     *
     * ссылка на видео
     * http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4
     */
//    val VIDEO_URL = "file:///Users/lkey/Desktop/videos/информатика.mp4"

    VRPopUp(
        onDismiss = onDismiss,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.8f)
                .clip(
                    GenericShape { size, _ ->
                        // Размер среза угла (можешь регулировать)
                        val cutX = size.width * 0.05f
                        val cutY = size.height * 0.1f

                        moveTo(0f, 0f)                 // левый верх
                        lineTo(size.width, 0f)         // правый верх
                        lineTo(size.width, size.height - cutY) // правая сторона вниз
                        lineTo(size.width - cutX, size.height) // срезанный угол
                        lineTo(0f, size.height)        // левый низ
                        close()
                    }
                )
                .background(Theme.colors.grayBackground)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (isShowingMovie && gameModel.movie != null) {
                    VideoPlayer(
                        url = gameModel.movie,
                        state = state,
                        onFinish = state::stopPlayback,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp),
                        bitmap = gameModel.image,
                        contentDescription = gameModel.text,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = gameModel.text,
                    color = Theme.colors.textInverse,
                    fontSize = 40.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "PROMO VIDEO",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable (
                            enabled = gameModel.movie != null
                        ) {
                            isShowingMovie = !isShowingMovie
                        }
                    ,
                    style = TextStyle(
                        color = Theme.colors.blueAction,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Start,
                        textDecoration = TextDecoration.Underline
                    )
                )

                Spacer(Modifier.height(20.dp))

                gameModel.description?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = it,
                        color = Theme.colors.secondaryText,
                        fontSize = 21.sp,
                        fontFamily = FontFamily(Font(Res.font.Light)),
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}
