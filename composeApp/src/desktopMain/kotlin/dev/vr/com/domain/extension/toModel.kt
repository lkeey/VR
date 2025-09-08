package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.domain.model.GameEntity
import dev.vr.com.domain.model.GameModel
import org.jetbrains.compose.resources.DrawableResource

fun GameEntity.toModel(
    imageConverter: (ByteArray) -> ImageBitmap
): GameModel {
    return GameModel(
        image = imageConverter(imageData),
        movie = videoPath,
        text = name,
        description = description
    )
}

fun GameModel.toEntity(
    imageConverter: (ImageBitmap) -> ByteArray
): GameEntity {
    return GameEntity(
        name = text,
        description = description,
        imageData = imageConverter(image),
        videoPath = movie
    )
}
