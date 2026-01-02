package dev.vr.com.core.components.item

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_open

@Composable
fun GridItem (
    modifier: Modifier,
    game: GameModel,
    index: Int,
    isEnableToDelete: Boolean,
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

    Box (
        modifier = modifier
            .alpha(alpha)
            .clickable (
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (!isEnableToDelete) {
                    onClick()
                }
            }
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 250.dp),
            bitmap = game.images.firstOrNull() ?: game.images.first(),
            contentDescription = game.text,
            contentScale = ContentScale.Crop
        )

        if (isEnableToDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "open",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(Theme.colors.pinkAction)
                    .padding(4.dp)
                    .clickable (
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onClick()
                    },
                tint = Color.White
            )

        } else {
            Icon(
                painter = painterResource(Res.drawable.ic_open),
                contentDescription = "open",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp),
                tint = Color.Unspecified
            )
        }
    }
}
