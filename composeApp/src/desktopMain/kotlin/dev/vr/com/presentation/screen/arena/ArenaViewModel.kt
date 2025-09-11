package dev.vr.com.presentation.screen.arena

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArenaViewModel (
    private val repository: GameRepository
) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)

    private val _state = MutableStateFlow(ArenaState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    init {
        loadGames()
    }

    fun onEvent(
        event: ArenaEvent
    ) {
        /*when (event) {

        }*/
    }

    private fun loadGames() {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            getGamesUseCase
                .invoke(categoryName = "Arena")
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { games ->
                    _state.update { it.copy(games = games, isLoading = false, error = null) }
                }

        }
    }


}
