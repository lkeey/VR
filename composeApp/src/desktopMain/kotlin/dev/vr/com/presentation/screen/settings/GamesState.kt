package dev.vr.com.presentation.screen.settings

import dev.vr.com.domain.model.GameModel

data class GamesState(
    val games: List<GameModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

