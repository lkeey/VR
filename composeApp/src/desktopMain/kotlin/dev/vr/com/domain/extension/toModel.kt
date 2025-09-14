package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.data.model.GameEntity
import dev.vr.com.presentation.model.GameModel

fun GameEntity.toModel(
    imageConverter: (ByteArray) -> ImageBitmap
): GameModel {
    return GameModel(
        id = id,
        image = imageConverter(imageData),
        movie = videoPath,
        text = name,
        description = description,
    )
}

