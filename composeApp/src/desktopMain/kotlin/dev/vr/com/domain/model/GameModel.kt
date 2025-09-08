package dev.vr.com.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class GameModel (
    val image: DrawableResource,
    val movie: String,
    val text: String,
    val description: String,
)
