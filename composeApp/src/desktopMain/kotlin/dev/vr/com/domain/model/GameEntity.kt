package dev.vr.com.domain.model

data class GameEntity(
    val id: Long = 0L,
    val name: String,
    val description: String,
    val imageData: ByteArray,
    val videoPath: String
)
