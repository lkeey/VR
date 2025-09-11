package dev.vr.com.presentation.screen.holidays

import dev.vr.com.presentation.model.GameModel

data class HolidaysState (
    val isLoading : Boolean = false,
    val holidays : List<GameModel> = emptyList(),
    val error: String? = null,
)
