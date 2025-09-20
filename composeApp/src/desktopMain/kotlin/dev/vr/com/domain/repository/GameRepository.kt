package dev.vr.com.domain.repository

import dev.vr.com.data.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getAllGames(): Flow<List<GameEntity>>

    fun getGamesByCategory(
        categoryName: String,
    ): Flow<List<GameEntity>>


    suspend fun addGame(
        gameEntity: GameEntity,
    )

    suspend fun deleteGame(id: Long)
}
