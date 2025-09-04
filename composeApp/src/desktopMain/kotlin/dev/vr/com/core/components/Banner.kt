package dev.vr.com.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun Banner(
    modifier: Modifier,
    background : Painter,
    title : @Composable () -> Unit,
    description : @Composable () -> Unit,
    button: @Composable () -> Unit,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = background,
            contentDescription = "banner background"
        )

        Column (
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            title.invoke()

            description.invoke()

            Spacer(
                Modifier.height(32.dp)
            )

            button.invoke()

        }

    }

}
