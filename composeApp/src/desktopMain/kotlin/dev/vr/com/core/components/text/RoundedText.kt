package dev.vr.com.core.components.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.rounded_1x

@Composable
fun RoundedText(
    content: @Composable () -> Unit,
    color: Color,
    modifier: Modifier = Modifier,
    shape : DrawableResource = Res.drawable.rounded_1x
) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(shape),
            tint = color,
            contentDescription = null,
        )

        content.invoke()
    }

}

