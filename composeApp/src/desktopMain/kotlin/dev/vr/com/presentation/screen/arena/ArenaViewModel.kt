package dev.vr.com.presentation.screen.arena

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.data.model.CategoryModel
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArenaViewModel (
    repository: GameRepository
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
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            // games
            getGamesUseCase
                .invoke(CategoryModel.ARENA)
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { games ->
                    _state.update { it.copy(games = games, isLoading = false, error = null) }
                }
        }

        viewModelScope.launch {
            //1st
            getGamesUseCase
                .invoke(CategoryModel.OUR_PARK)
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { items1st ->
                    _state.update { it.copy(items1st = items1st, isLoading = false, error = null) }
                }
        }

        viewModelScope.launch {
            //2nd
            getGamesUseCase
                .invoke(CategoryModel.OUR_GAMES)
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { items2nd ->
                    _state.update { it.copy(items2nd = items2nd, isLoading = false, error = null) }
                }
        }

    }


}
