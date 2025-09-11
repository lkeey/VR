package dev.vr.com.presentation.screen.settings

import java.io.File

sealed interface SettingsEvent {
    data class OnEnterGameName(val name: String) : SettingsEvent
    data class OnChoseGameCategory(val category: String) : SettingsEvent
    data class OnEnterGameDescription(val description: String) : SettingsEvent
    data class OnEnterGameMoviePath(val movie: String) : SettingsEvent

    data class OnChooseImage(val file: File?) : SettingsEvent
    data class DeleteGame(val id: Long) : SettingsEvent
    data object OnAddGame : SettingsEvent

}
