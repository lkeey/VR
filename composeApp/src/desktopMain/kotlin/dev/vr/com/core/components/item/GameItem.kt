package dev.vr.com.core.components.item

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.Font
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res

@Composable
fun GameItem (
    modifier: Modifier,
    game: GameModel,
    index: Int,
    onClick: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }

    // запускаем анимацию после компоновки
    LaunchedEffect(Unit) {
        visible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = index * 100
        )
    )

    Box(
        modifier = modifier
            .alpha(alpha)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomCenter,
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            bitmap = game.image,
            contentDescription = "game image",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = game.text,
                fontWeight = FontWeight(700),
                fontSize = 36.sp,
                fontFamily = FontFamily(Font(Res.font.Bold)),
                color = Theme.colors.textInverse,
            )

            Spacer(
                Modifier.height(8.dp)
            )

            RoundedButton(
                color = when (index % 2) {
                    0 -> Theme.colors.blueAction
                    else -> Theme.colors.pinkAction
                },
                content = {
                    Text(
                        text = "СМОТРЕТЬ",
                        color = Theme.colors.textInverse,
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )
                },
                innerPadding = PaddingValues(
                    horizontal = 32.dp,
                    vertical = 8.dp
                )
            ) {
                onClick()
            }
        }
    }

}
