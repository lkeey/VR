package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.data.model.GameEntity
import dev.vr.com.presentation.model.GameModel

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
