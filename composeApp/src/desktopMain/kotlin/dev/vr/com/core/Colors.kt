package dev.vr.com.core

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class VRColors(
    val primaryBackground: Color = Color.Black,
    val secondaryBackground: Color = Color.Black,
    val secondaryBorder: Color = Color.Black,
    val errorColor: Color = Color.Black,
    val primaryAction: Color = Color.Black,
    val defaultText: Color = Color.Black,
    val editPlaceholder: Color = Color.Black,
    val blackProfile: Color = Color.Black,
    val secondaryScreen: Color = Color.Black,
    val greyDescription: Color = Color.Black,
    val backgroundMain: Color = Color.Black,

    val primaryTextColor: Color = Color.Black,
    val hintTextColor: Color = Color.Black,
    val highlightTextColor: Color = Color.Black,
    val secondaryTextColor: Color = Color.Black,
    val thirdTextColor: Color = Color.Black,
    val tagColor: Color = Color.Black,
    val tagTextColor: Color = Color.Black
)

val lightPalette = VRColors(
    primaryBackground = Color.LightGray,
)

val darkPalette = VRColors(
    primaryBackground = Color.Black,
)

val LocalColorProvider = staticCompositionLocalOf<VRColors> { error("No default implementation") }

