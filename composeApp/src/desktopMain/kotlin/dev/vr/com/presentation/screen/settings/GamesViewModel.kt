package dev.vr.com.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.AddGameUseCase
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GamesViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)
    private val addGameUseCase = AddGameUseCase(repository)

    private val _state = MutableStateFlow(GamesState())
    val state: StateFlow<GamesState> = _state

    init {
        loadGames()
    }

    fun onEvent(
        event: GamesEvent
    ) {
        when (event) {
            is GamesEvent.AddGame -> addGame(event.game)
            is GamesEvent.DeleteGame -> deleteGame(event.id)
        }
    }

    private fun loadGames() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            /*getGamesUseCase
                .invoke()
                .onSuccess { flow ->
                    flow.catch { error ->
                        _state.update {
                            it.copy(
                                error = error.message,
                                isLoading = false
                            )
                        }
                    }.collect { games ->
                        _state.update {
                            it.copy(
                                games = games,
                                error = null,
                                isLoading = false
                            )
                        }
                    }

                }.onFailure { exception ->
                    _state.update {
                        it.copy(
                            error = exception.message,
                            isLoading = false
                        )
                    }
                }*/

            getGamesUseCase.invoke()
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { games ->
                    _state.update { it.copy(games = games, isLoading = false, error = null) }
                }
            }
    }

    private fun addGame(game: GameModel) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            addGameUseCase
                .invoke(game = game)
                .onSuccess {
                    loadGames()
                }
                .onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message
                        )
                    }
                }


        }
    }

    private fun deleteGame(id: Long) {
        viewModelScope.launch {
            repository.deleteGame(id)
        }
    }


}


