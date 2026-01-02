package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import dev.vr.com.data.model.GameEntity
import dev.vr.com.presentation.model.GameModel

fun GameEntity.toModel(
    imageConverter: (ByteArray) -> List<ImageBitmap>
): GameModel {
    return GameModel(
        id = id,
        images = imageConverter(imagesData),
        movie = videoPath,
        text = name,
        description = description,
    )
}

