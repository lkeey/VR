package dev.vr.com.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun VRTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides if (isSystemInDarkTheme()) darkPalette else lightPalette,
        content = content
    )
}

object Theme {
    val colors: VRColors
        @Composable
        get() = LocalColorProvider.current
}
