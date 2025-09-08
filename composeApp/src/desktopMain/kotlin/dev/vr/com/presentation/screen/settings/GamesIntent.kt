package dev.vr.com.presentation.screen.settings

import dev.vr.com.domain.model.GameModel

sealed class GamesIntent {
    object LoadGames : GamesIntent()
    data class AddGame(val game: GameModel) : GamesIntent()
    data class DeleteGame(val id: Long) : GamesIntent()
}
