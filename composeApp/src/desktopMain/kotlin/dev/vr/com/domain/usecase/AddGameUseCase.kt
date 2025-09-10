package dev.vr.com.domain.usecase

import dev.vr.com.domain.extension.toByteArray
import dev.vr.com.domain.extension.toEntity
import dev.vr.com.presentation.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddGameUseCase (
    private val repository: GameRepository
) {

    suspend fun invoke(
        game: GameModel
    ) : Result<Unit> =
        withContext(Dispatchers.IO) {
             try {
                println("Saving game: name=${game.text}, description=${game.description}, video=${game.movie}, image=${game.image.width}x${game.image.height}")

                repository.addGame(
                    game.toEntity(
                        imageConverter = { it.toByteArray() }
                    )
                )

                Result.success(Unit)
            } catch (e : Exception) {
                println(e.printStackTrace())
                Result.failure(e)
            }

        }

}
