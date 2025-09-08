package dev.vr.com.presentation.screen.settings

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.vr.com.domain.extension.toByteArray
import dev.vr.com.domain.extension.toEntity
import dev.vr.com.domain.extension.toModel
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

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
                        games = games.map { gameEntity -> gameEntity.toModel(
                            imageConverter = { bytes ->
                                val inputStream = ByteArrayInputStream(bytes)
                                val buffered = ImageIO.read(inputStream)
                                    ?: throw IllegalArgumentException("Invalid image bytes")
                                buffered.toComposeImageBitmap()
                            }
                        ) },
                        isLoading = false
                    )
                }
        }
    }

    private fun addGame(game: GameModel) {
        viewModelScope.launch {
            repository.addGame(
                game.toEntity { drawable ->
                    (drawable as ImageBitmap).toByteArray()
                }
            )
            loadGames() // refresh list after adding
        }
    }

    private fun deleteGame(id: Long) {
        viewModelScope.launch {
            repository.deleteGame(id)
        }
    }

    private fun BufferedImage.toComposeImageBitmap(): ImageBitmap {
        return ImageBitmap(width = this.width, height = this.height, config = ImageBitmapConfig.Argb8888).also { bmp ->
            val pixels = IntArray(width * height)
            this.getRGB(0, 0, width, height, pixels, 0, width)
            bmp.readPixels(pixels, 0, width, 0, 0, width, height)
        }
    }
}


