package dev.vr.com.core.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import vr.composeapp.generated.resources.PressStart
import vr.composeapp.generated.resources.Res

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        ),
        shape = GenericShape { size, _ ->
            val cutSizeX = size.width * 0.2f
            val cutSizeY = size.height * 0.2f

            moveTo(cutSizeX, 0f) // Сдвинутый верхний левый угол
            lineTo(size.width, 0f)
            lineTo(size.width, size.height - cutSizeY) // Сдвинутый нижний правый угол
            lineTo(size.width - cutSizeX, size.height)
            lineTo(0f, size.height)
            lineTo(0f, cutSizeY)
            close()
        },
        modifier = modifier
            .padding(0.dp)
            .height(40.dp)
            .wrapContentWidth()
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.PressStart)),
            fontWeight = FontWeight.Bold
        )
    }
}