package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import javax.imageio.ImageIO

fun ImageBitmap.toByteArray(): ByteArray {
    require(width > 0 && height > 0) { "Image has invalid size: ${width}x${height}" }

    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val pixels = IntArray(width * height)

    this.readPixels(pixels, stride = width, startX = 0, startY = 0, width = width, height = height)

    bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)

    return ByteArrayOutputStream().use { output ->
        ImageIO.write(bufferedImage, "png", output)
        output.toByteArray()
    }
}


fun byteArrayToImageBitmap(bytes: ByteArray): ImageBitmap {
    val bufferedImage: BufferedImage =
        ImageIO.read(ByteArrayInputStream(bytes)) ?: throw IllegalArgumentException("Invalid image bytes")

    return bufferedImage.toComposeImageBitmap()
}

/**
 * Serializes a list of ImageBitmaps into a single ByteArray
 * Format: [4 bytes: count][4 bytes: length1][bytes1][4 bytes: length2][bytes2]...
 */
fun List<ImageBitmap>.toSerializedByteArray(): ByteArray {
    if (isEmpty()) {
        throw IllegalArgumentException("Cannot serialize empty image list")
    }

    val output = ByteArrayOutputStream()
    
    // Write count of images (4 bytes)
    val countBuffer = ByteBuffer.allocate(4).putInt(size)
    output.write(countBuffer.array())
    
    // Write each image
    forEach { imageBitmap ->
        val imageBytes = imageBitmap.toByteArray()
        
        // Write length of this image (4 bytes)
        val lengthBuffer = ByteBuffer.allocate(4).putInt(imageBytes.size)
        output.write(lengthBuffer.array())
        
        // Write image bytes
        output.write(imageBytes)
    }
    
    return output.toByteArray()
}

/**
 * Deserializes a ByteArray into a list of ImageBitmaps
 * Format: [4 bytes: count][4 bytes: length1][bytes1][4 bytes: length2][bytes2]...
 */
fun deserializeImageBitmaps(bytes: ByteArray): List<ImageBitmap> {
    if (bytes.size < 4) {
        throw IllegalArgumentException("Invalid serialized images data: too short")
    }
    
    val buffer = ByteBuffer.wrap(bytes)
    
    // Read count of images
    val count = buffer.int
    
    if (count <= 0) {
        throw IllegalArgumentException("Invalid image count: $count")
    }
    
    val images = mutableListOf<ImageBitmap>()
    
    // Read each image
    repeat(count) {
        // Read length of this image
        if (buffer.remaining() < 4) {
            throw IllegalArgumentException("Invalid serialized images data: incomplete length field")
        }
        val length = buffer.int
        
        if (length <= 0 || length > buffer.remaining()) {
            throw IllegalArgumentException("Invalid image length: $length")
        }
        
        // Read image bytes
        val imageBytes = ByteArray(length)
        buffer.get(imageBytes)
        
        // Convert to ImageBitmap
        images.add(byteArrayToImageBitmap(imageBytes))
    }
    
    return images
}
