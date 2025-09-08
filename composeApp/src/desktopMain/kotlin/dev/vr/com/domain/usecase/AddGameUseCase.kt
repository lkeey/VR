package dev.vr.com.domain.usecase

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.data.repository.GameRepositoryImpl
import dev.vr.com.domain.extension.toByteArray
import dev.vr.com.domain.extension.toEntity
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.repository.GameRepository

class AddGameUseCase (
    private val repository: GameRepository
) {

    suspend fun invoke(
        game: GameModel
    ) {
        repository.addGame(
            game.toEntity(
                imageConverter = { drawable ->
                    // convert drawable to ByteArray
                    (drawable as ImageBitmap).toByteArray()
                }
            )
        )
    }

}
