package dev.vr.com.presentation.screen.settings

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.data.model.CategoryModel
import dev.vr.com.presentation.model.GameModel

data class SettingsState(
    val games: List<GameModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val gameName: String? = null,
    val gameDescription: String? = null,
    val gameMovieUrl: String? = null,
    val gameImage: ImageBitmap? = null,
    val gameCategory: CategoryModel? = null
)
