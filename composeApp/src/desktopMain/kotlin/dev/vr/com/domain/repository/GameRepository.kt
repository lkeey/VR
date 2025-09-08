package dev.vr.com.domain.repository

import dev.vr.com.domain.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun getAllGames(): Flow<List<GameEntity>>

    suspend fun addGame(gameEntity: GameEntity)

    suspend fun deleteGame(id: Long)
}
