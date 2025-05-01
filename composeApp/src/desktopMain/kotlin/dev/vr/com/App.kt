package dev.vr.com

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.vr.com.core.Theme
import dev.vr.com.core.VRTheme
import dev.vr.com.core.components.RoundedButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.allDrawableResources
import vr.composeapp.generated.resources.compose_multiplatform
import vr.composeapp.generated.resources.`Обложка 10`

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
        }
    }
}