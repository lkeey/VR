package dev.vr.com.presentation

import Carousel
import androidx.compose.runtime.Composable
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.img1
import vr.composeapp.generated.resources.img2
import vr.composeapp.generated.resources.img3

@Composable
fun ArenaScreen() {
    val images = listOf(Res.drawable.img1, Res.drawable.img2, Res.drawable.img3, Res.drawable.img1, Res.drawable.img2)

    Carousel(imageUrls = images)
}