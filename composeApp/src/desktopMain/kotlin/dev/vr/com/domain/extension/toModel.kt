package dev.vr.com.domain.extension

import dev.vr.com.domain.model.GameEntity
import dev.vr.com.domain.model.GameModel
import org.jetbrains.compose.resources.DrawableResource

fun GameEntity.toModel(
    imageConverter: (ByteArray) -> DrawableResource
): GameModel {
    return GameModel(
        image = imageConverter(imageData),
        movie = videoPath,
        text = name,
        description = description
    )
}

fun GameModel.toEntity(
    id: Long = 0L,
    imageConverter: (DrawableResource) -> ByteArray
): GameEntity {
    return GameEntity(
        id = id,
        name = text,
        description = description,
        imageData = imageConverter(image),
        videoPath = movie
    )
}
