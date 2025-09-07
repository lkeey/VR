package dev.vr.com.core.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.corner.SkewedCutParallelogramShape

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    color: Color,
    innerPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 12.dp
    ),
    skew: Dp = 12.dp,
    cutTL: Dp = 8.dp,
    cutTR: Dp = 8.dp,
    cutBR: Dp = 20.dp,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {

    val shape = SkewedCutParallelogramShape(skew = skew, cutTopLeft = cutTL, cutTopRight = cutTR, cutBottomRight = cutBR)

    Box(
        modifier = modifier
            .background(
                color = color,
                shape = shape
            )
            .padding(innerPadding)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            content.invoke()
        }
    }

}
