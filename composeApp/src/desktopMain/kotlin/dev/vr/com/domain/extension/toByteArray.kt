package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

fun ImageBitmap.toByteArray(): ByteArray {
    require(width > 0 && height > 0) { "Image has invalid size: ${width}x${height}" }

    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val pixels = IntArray(width * height)

    // stride = ширина картинки, если пиксели плотно без паддинга
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
