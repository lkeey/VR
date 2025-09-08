package dev.vr.com.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GamesViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GamesState())
    val state: StateFlow<GamesState> = _state

    fun onIntent(intent: GamesIntent) {
        when (intent) {
            is GamesIntent.LoadGames -> loadGames()
            is GamesIntent.AddGame -> addGame(intent.game)
            is GamesIntent.DeleteGame -> deleteGame(intent.id)
        }
    }

    private fun loadGames() {
        viewModelScope.launch {
            repository.getAllGames()
                .onStart { _state.value = _state.value.copy(isLoading = true) }
                .catch { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Ошибка загрузки"
                    )
                }
                .collect { games ->
                    _state.value = GamesState(
                        games = games,
                        isLoading = false
                    )
                }
        }
    }

    private fun addGame(game: GameModel) {
        viewModelScope.launch {
            repository.addGame(game)
        }
    }

    private fun deleteGame(id: Long) {
        viewModelScope.launch {
            repository.deleteGame(id)
        }
    }
}
