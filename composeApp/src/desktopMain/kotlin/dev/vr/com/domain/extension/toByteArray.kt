package dev.vr.com.domain.extension

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

fun ImageBitmap.toByteArray(): ByteArray {
    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val pixels = IntArray(width * height)
    this.readPixels(pixels, 0, width, 0, 0, width, height)
    bufferedImage.setRGB(0, 0, width, height, pixels, 0, width)
    val output = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", output)
    return output.toByteArray()
}

fun byteArrayToImageBitmap(bytes: ByteArray): ImageBitmap {
    val bufferedImage: BufferedImage =
        ImageIO.read(ByteArrayInputStream(bytes)) ?: throw IllegalArgumentException("Invalid image bytes")

    return bufferedImage.toComposeImageBitmap()
}

fun BufferedImage.toComposeImageBitmap(): ImageBitmap {
    return this.toComposeImageBitmap()
}