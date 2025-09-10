package dev.vr.com.presentation.screen.settings

import dev.vr.com.domain.model.GameModel

sealed class GamesEvent {
    data class AddGame(val game: GameModel) : GamesEvent()
    data class DeleteGame(val id: Long) : GamesEvent()
}
