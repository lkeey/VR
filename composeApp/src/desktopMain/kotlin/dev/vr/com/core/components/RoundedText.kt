package dev.vr.com.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.rounded

@Composable
fun RoundedText(
    content: @Composable () -> Unit,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    shape : DrawableResource = Res.drawable.rounded
) {
    Box (
        modifier = modifier,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Icon(
            painter = painterResource(shape),
            tint = color,
            contentDescription = null
        )

        content.invoke()
    }

}

