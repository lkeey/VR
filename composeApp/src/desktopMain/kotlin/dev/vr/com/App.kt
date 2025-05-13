package dev.vr.com

import Carousel
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.vr.com.core.Theme
import dev.vr.com.core.VRTheme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.navigation.Route
import dev.vr.com.presentation.ArenaScreen
import dev.vr.com.presentation.HolidaysScreen
import dev.vr.com.presentation.ZoneScreen
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun App() {

    val navController = rememberNavController()

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

            NavHost(
                navController = navController,
                startDestination = Route.Zone,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                composable<Route.Zone>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    ZoneScreen(
                        navController = navController
                    )
                }

                composable<Route.Arena>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    ArenaScreen(
                        navController = navController
                    )
                }

                composable<Route.Holidays>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    HolidaysScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}
