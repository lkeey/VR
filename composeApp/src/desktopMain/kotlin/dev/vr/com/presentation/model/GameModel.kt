package dev.vr.com.presentation.model

import androidx.compose.ui.graphics.ImageBitmap

/* presentation game model */
data class GameModel (
    val id: Long,
    val images: List<ImageBitmap>,
    val movie: String?,
    val text: String,
    val description: String?,
)