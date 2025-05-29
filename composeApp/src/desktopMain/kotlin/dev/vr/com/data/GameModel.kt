package dev.vr.com.data

import org.jetbrains.compose.resources.DrawableResource

data class GameModel (
    val image: DrawableResource,
    val movie: String,
    val text: String,
    val description: String,
)