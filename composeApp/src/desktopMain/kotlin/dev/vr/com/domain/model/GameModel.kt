package dev.vr.com.domain.model

import androidx.compose.ui.graphics.ImageBitmap

data class GameModel (
    val image: ImageBitmap,
    val movie: String,
    val text: String,
    val description: String,
)
