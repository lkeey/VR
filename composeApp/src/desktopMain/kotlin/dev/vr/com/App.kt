package dev.vr.com

import Carousel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import dev.vr.com.core.Theme
import dev.vr.com.core.VRTheme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.presentation.ArenaScreen
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun App() {

    VRTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.primaryBackground)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                RoundedButton(
                    text = "VR ARENA",
                    color = Theme.colors.blueAction,
                ) {

                }

                RoundedButton(
                    text = "VR ZONE",
                    color = Theme.colors.pinkAction,
                ) {

                }

                RoundedButton(
                    text = "ПРАЗДНИКИ И МЕРОПРИЯТИЯ",
                    color = Theme.colors.blueAction,
                ) {

                }

                RoundedButton(
                    text = "НАСТРОЙКИ",
                    color = Theme.colors.pinkAction,
                ) {

                }
            }

            Image(
                modifier = Modifier
                    .fillMaxWidth(1f),
                painter = painterResource(Res.drawable.`Обложка 10`),
                contentDescription = "main image"
            )

            ArenaScreen()
        }
    }
}
