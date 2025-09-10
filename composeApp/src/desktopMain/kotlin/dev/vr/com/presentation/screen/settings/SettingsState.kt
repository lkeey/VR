package dev.vr.com.presentation.screen.settings

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.domain.model.GameModel

data class SettingsState(
    val games: List<GameModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val gameName: String = "",
    val gameDescription: String = "",
    val gameMovieUrl: String = "",
    val gameImage: ImageBitmap? = null
)
