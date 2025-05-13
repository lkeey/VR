package dev.vr.com.core

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatedGradientCirclesBackground() {
    val circles = remember { createSideGradientCircles() }
    val time = rememberInfiniteTransition(label = "circleTime")

    val animatedHue by time.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "hue"
    )

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {

        circles.forEach { circle ->
            val dynamicColor = Color.hsv(
                (animatedHue + circle.hueShift) % 360,
                0.7f,
                1f,
                0.25f
            )

            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(dynamicColor, Color.Transparent),
                    center = circle.position(size),
                    radius = circle.radius
                ),
                center = circle.position(size),
                radius = circle.radius
            )
        }
    }
}
data class SideGradientCircle(
    val alignment: Alignment,
    val radius: Float,
    val hueShift: Float
) {
    fun position(canvasSize: Size): Offset {
        return when (alignment) {
            Alignment.TopStart -> Offset(0f, 0f)
            Alignment.CenterStart -> Offset(0f, canvasSize.height / 2)
            Alignment.BottomStart -> Offset(0f, canvasSize.height)
            Alignment.TopEnd -> Offset(canvasSize.width, 0f)
            Alignment.CenterEnd -> Offset(canvasSize.width, canvasSize.height / 2)
            Alignment.BottomEnd -> Offset(canvasSize.width, canvasSize.height)
            else -> Offset.Zero
        }
    }
}

fun createSideGradientCircles(): List<SideGradientCircle> {
    return listOf(
        SideGradientCircle(Alignment.TopStart, 600f, 0f),
        SideGradientCircle(Alignment.CenterStart, 800f, 60f),
        SideGradientCircle(Alignment.BottomStart, 700f, 120f),
        SideGradientCircle(Alignment.TopEnd, 600f, 180f),
        SideGradientCircle(Alignment.CenterEnd, 900f, 240f),
        SideGradientCircle(Alignment.BottomEnd, 700f, 300f)
    )
}
