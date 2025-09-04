package dev.vr.com.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.Theme
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.rounded

@Composable
fun RoundedText(
    text: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box (
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.rounded),
            tint = color,
            contentDescription = null
        )

        Text(
            text = text,
            color = Theme.colors.textInverse,
            fontSize = 24.sp,
            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
            fontWeight = FontWeight(700),
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }

}

