package dev.vr.com.data.repository

import app.cash.sqldelight.coroutines.asFlow
import dev.vr.com.db.VRDatabase
import dev.vr.com.domain.model.GameEntity
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    private val db: VRDatabase
) : GameRepository {

    private val queries = db.gameQueries

    override fun getAllGames(): Flow<List<GameEntity>> {
        return queries.selectAllGames()
            .asFlow()
            .map { query ->
                query.executeAsList().map {
                    GameEntity(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        imageData = it.image,
                        videoPath = it.video_path
                    )
                }
            }
    }

    override suspend fun addGame(gameEntity: GameEntity) {
        queries.insertGame(
            name = gameEntity.name,
            description = gameEntity.description,
            image = gameEntity.imageData,
            video_path = gameEntity.videoPath
        )
    }

    override suspend fun deleteGame(id: Long) {
        queries.deleteGameById(id)
    }
}