package dev.vr.com.domain.usecase

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.domain.extension.byteArrayToImageBitmap
import dev.vr.com.domain.extension.toModel
import dev.vr.com.presentation.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetGamesUseCase (
    private val repository: GameRepository
) {

     fun invoke() : Flow<List<GameModel>> =
        repository
            .getAllGames()
            .map { list ->
                list.map {
                    it.toModel(
                        imageConverter = { bytes ->
                            byteArrayToImageBitmap(bytes)
                        }
                    )
                }
            }
            .flowOn(Dispatchers.IO)

}
