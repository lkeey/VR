package dev.vr.com.domain.usecase

import dev.vr.com.data.model.CategoryModel
import dev.vr.com.data.model.GameEntity
import dev.vr.com.domain.extension.toSerializedByteArray
import dev.vr.com.domain.repository.GameRepository
import dev.vr.com.presentation.model.GameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddGameUseCase (
    private val repository: GameRepository
) {

    suspend fun invoke(
        game: GameModel,
        category: CategoryModel
    ) : Result<Unit> =
        withContext(Dispatchers.IO) {
             try {
                println("Saving game: name=${game.text}, description=${game.description}, video=${game.movie}, images count=${game.images.size}")

                repository.addGame(
                    gameEntity = GameEntity(
                        name = game.text,
                        description = game.description,
                        imagesData = game.images.toSerializedByteArray(),
                        videoPath = game.movie,
                        categoryName = category.key
                    )
                )

                Result.success(Unit)
            } catch (e : Exception) {
                println(e.printStackTrace())
                Result.failure(e)
            }

        }

}
