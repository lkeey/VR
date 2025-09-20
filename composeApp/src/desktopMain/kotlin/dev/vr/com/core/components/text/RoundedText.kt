package dev.vr.com.core.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.corner.SkewedCutParallelogramShape

@Composable
fun RoundedText(
    modifier: Modifier = Modifier,
    color: Color,
    skew: Dp = 12.dp,
    cutTL: Dp = 8.dp,
    cutTR: Dp = 8.dp,
    cutBR: Dp = 20.dp,
    innerPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 12.dp
    ),
    content: @Composable () -> Unit,
) {

    val shape = SkewedCutParallelogramShape(skew = skew, cutTopLeft = cutTL, cutTopRight = cutTR, cutBottomRight = cutBR)

    Box (
        modifier = modifier
            .background(
                color = color,
                shape = shape
            )
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }

}
