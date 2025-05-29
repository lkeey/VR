package dev.vr.com.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.img1
import vr.composeapp.generated.resources.img2
import vr.composeapp.generated.resources.img3

@Composable
fun ZoneScreen(
    navController: NavController
) {
        Text("ZONE", color = White)

}