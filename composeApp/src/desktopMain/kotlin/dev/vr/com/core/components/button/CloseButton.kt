package dev.vr.com.core.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_close

@Composable
fun CloseButton (
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Icon(
        painter = painterResource(Res.drawable.ic_close),
        contentDescription = "close",
        tint = Color.Unspecified,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onDismiss()
            }
    )
}
