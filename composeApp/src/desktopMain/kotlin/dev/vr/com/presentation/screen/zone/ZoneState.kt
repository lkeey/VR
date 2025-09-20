package dev.vr.com.presentation.screen.zone

import dev.vr.com.presentation.model.GameModel

data class ZoneState (
    val isLoading : Boolean = false,
    val games : List<GameModel> = emptyList(),
    val error: String? = null,
)
