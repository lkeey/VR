package dev.vr.com.core.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.rounded

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    color: Color,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.rounded),
            tint = color,
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            content.invoke()
        }
    }

}