package dev.vr.com.presentation.screen.settings

import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.presentation.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.AddGameUseCase
import dev.vr.com.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.imageio.ImageIO

class SettingsViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)
    private val addGameUseCase = AddGameUseCase(repository)

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    init {
        loadGames()
    }

    fun onEvent(
        event: SettingsEvent
    ) {
        when (event) {
            SettingsEvent.OnAddGame -> addGame()

            is SettingsEvent.DeleteGame -> deleteGame(event.id)

            is SettingsEvent.OnChooseImage -> {

                if (event.file != null) {
                    val buffered = ImageIO.read(event.file)
                    _state.update {
                        it.copy(
                            gameImage = buffered.toComposeImageBitmap()
                        )
                    }
                } else {
                   /* TODO SHOW ERROR */
                }
            }

            is SettingsEvent.OnEnterGameDescription -> {
                _state.update {
                    it.copy(
                        gameDescription = event.description
                    )
                }
            }
            is SettingsEvent.OnEnterGameMoviePath -> {
                _state.update {
                    it.copy(
                        gameMovieUrl = event.movie
                    )
                }
            }
            is SettingsEvent.OnEnterGameName -> {
                _state.update {
                    it.copy(
                        gameName = event.name
                    )
                }
            }
        }
    }

    private fun loadGames() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            getGamesUseCase.invoke()
                .catch { e ->
                    _state.update { it.copy(error = e.message, isLoading = false) }
                }
                .collect { games ->
                    _state.update { it.copy(games = games, isLoading = false, error = null) }
                }
            }
    }

    private fun addGame() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            addGameUseCase
                .invoke(
                    game = GameModel(
                        text = state.value.gameName,
                        image = state.value.gameImage ?: throw Exception("no image"),
                        description = state.value.gameDescription,
                        movie = state.value.gameMovieUrl
                    )
                )
                .onSuccess {
                    _state.update {
                        it.copy(
                            gameName = "",
                            gameImage = null,
                            gameDescription = "",
                            gameMovieUrl = ""
                        )
                    }

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


