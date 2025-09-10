package dev.vr.com.domain.usecase

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.domain.extension.byteArrayToImageBitmap
import dev.vr.com.domain.extension.toModel
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGamesUseCase (
    private val repository: GameRepository
) {

    private val imageConverter: (ByteArray) -> ImageBitmap = { bytes ->
        byteArrayToImageBitmap(bytes)
    }

     fun invoke() : Flow<List<GameModel>> {

            return repository
                    .getAllGames()
                    .map { list ->
                        list.map {
                            it.toModel(imageConverter)
                        }
                    }


//        } catch (e : Exception) {
//            Result.failure(e)
//        }

    }
}
