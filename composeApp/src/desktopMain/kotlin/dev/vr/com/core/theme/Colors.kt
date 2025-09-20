package dev.vr.com.core.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class VRColors(
    val primaryBackground: Color = Color.Black,
    val pinkAction: Color = Color.Black,
    val blueAction: Color = Color.Black,
    val grayBackground: Color = Color.Black,
    val textInverse: Color = Color.Black,
    val secondaryText: Color = Color.Black,
    val secondaryGray: Color = Color.Black,
)

val lightPalette = VRColors(
    primaryBackground = Color.Black,
    pinkAction = Color(0xFFD81950),
    blueAction = Color(0xFF0084AC),
    grayBackground = Color(0xFF1E1E1E),
    textInverse = Color.White,
    secondaryText = Color(0xFFB3B3B3),
    secondaryGray = Color(0xFF383838),
)

val darkPalette = VRColors(
    primaryBackground = Color.Black,
    pinkAction = Color(0xFFE83E6B),
    blueAction = Color(0xFF0C81B0),
    grayBackground = Color(0xFF1E1E1E),
    textInverse = Color.White,
    secondaryText = Color(0xFFB3B3B3),
    secondaryGray = Color(0xFF383838),
)

val LocalColorProvider = staticCompositionLocalOf<VRColors> { error("No default implementation") }

