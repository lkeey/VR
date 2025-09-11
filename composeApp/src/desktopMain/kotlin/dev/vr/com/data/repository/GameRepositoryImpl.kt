package dev.vr.com.data.repository

import app.cash.sqldelight.coroutines.asFlow
import dev.vr.com.db.VRDatabase
import dev.vr.com.data.model.GameEntity
import dev.vr.com.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(
    db: VRDatabase
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
                        videoPath = it.video_path,
                        categoryName = it.category
                    )
                }
            }
    }

    override fun getGamesByCategory(categoryName: String): Flow<List<GameEntity>> {
        return queries.selectGamesByCategory(categoryName)
            .asFlow()
            .map { query ->
                query.executeAsList().map {
                    GameEntity(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        imageData = it.image,
                        videoPath = it.video_path,
                        categoryName = it.category
                    )
                }
            }
    }

    override suspend fun addGame(
        gameEntity: GameEntity,
    ) {
        queries.insertGame(
            name = gameEntity.name,
            description = gameEntity.description,
            image = gameEntity.imageData,
            video_path = gameEntity.videoPath,
            category = gameEntity.categoryName
        )
    }

    override suspend fun deleteGame(id: Long) {
        queries.deleteGameById(id)
    }
}