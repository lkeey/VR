package dev.vr.com.presentation.screen.arena

import dev.vr.com.presentation.model.GameModel

data class ArenaState (
    val isLoading : Boolean = false,
    val games : List<GameModel> = emptyList(),
    val items1st : List<GameModel> = emptyList(),
    val items2nd : List<GameModel> = emptyList(),
    val error: String? = null,
)
