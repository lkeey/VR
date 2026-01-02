package dev.vr.com.presentation.screen.settings

import dev.vr.com.data.model.CategoryModel
import java.io.File

sealed interface SettingsEvent {
    data class OnEnterGameName(val name: String) : SettingsEvent
    data class OnChoseGameCategory(val category: CategoryModel) : SettingsEvent
    data class OnEnterGameDescription(val description: String) : SettingsEvent
    data class OnEnterGameMoviePath(val movie: String) : SettingsEvent

    data class OnAddImage(val file: File?) : SettingsEvent
    data class OnRemoveImage(val index: Int) : SettingsEvent
    data class DeleteGame(val id: Long) : SettingsEvent
    data object OnAddGame : SettingsEvent

}
