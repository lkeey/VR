package dev.vr.com.presentation.screen.zone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ZoneViewModel (
    repository: GameRepository

) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)

    private val _state = MutableStateFlow(ZoneState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            getGamesUseCase
                .invoke(categoryName = "Zone")
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { games ->
                    _state.update { it.copy(games = games, isLoading = false, error = null) }
                }

        }
    }
}
