package dev.vr.com.presentation.screen.settings

import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.domain.usecase.AddGameUseCase
import dev.vr.com.domain.usecase.GetGamesUseCase
import dev.vr.com.presentation.model.GameModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.imageio.ImageIO

class SettingsViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val getGamesUseCase = GetGamesUseCase(repository)
    private val addGameUseCase = AddGameUseCase(repository)

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

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

            is SettingsEvent.OnChoseGameCategory -> {
                _state.update {
                    it.copy(
                        gameCategory = event.category
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

            try {
                addGameUseCase
                    .invoke(
                        game = GameModel(
                            id = 0L,
                            text = state.value.gameName ?: throw Exception("Обязательно укажите название") ,
                            image = state.value.gameImage ?: throw Exception("Обязательно добавьте изображение"),
                            description = state.value.gameDescription ?: "",
                            movie = state.value.gameMovieUrl ?: ""
                        ),
                        category = state.value.gameCategory ?: throw Exception("Обязательно выберите раздел"),
                    )
                    .onSuccess {
//                    _state.update {
//                        it.copy(
//                            gameName = null,
//                            gameImage = null,
//                            gameDescription = null,
//                            gameMovieUrl = null,
//                            gameCategory = null,
//                        )
//                    }

//                        loadGames()
                    }
                    .onFailure { exception ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message
                            )
                        }
                    }
            } catch (e : Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
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


