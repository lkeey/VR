package dev.vr.com.data.model

/* game model saved into local db */
data class GameEntity(
    val id: Long = 0L,
    val name: String,
    val description: String?,
    val imageData: ByteArray,
    val videoPath: String?,
    val categoryName: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (!imageData.contentEquals(other.imageData)) return false
        if (videoPath != other.videoPath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageData.contentHashCode()
        result = 31 * result + videoPath.hashCode()
        return result
    }
}