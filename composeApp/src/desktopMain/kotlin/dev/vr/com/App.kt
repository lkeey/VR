package dev.vr.com

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vr.com.core.AnimatedGradientCirclesBackground
import dev.vr.com.core.Theme
import dev.vr.com.core.VRTheme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.navigation.Route
import dev.vr.com.presentation.ArenaScreen
import dev.vr.com.presentation.HolidaysScreen
import dev.vr.com.presentation.ZoneScreen
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.logo_vs_arena
import vr.composeapp.generated.resources.`Обложка 10`

@Composable
fun App() {

    val navController = rememberNavController()

    VRTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedGradientCirclesBackground()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Theme.colors.primaryBackground)
            ) {

                /* TopBar */

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
                        .background(Theme.colors.grayBackground)
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(Res.drawable.logo_vs_arena),
                        contentDescription = "logo"
                    )

                    RoundedButton(
                        text = "VR ARENA",
                        color = Theme.colors.blueAction,
                    ) {
                        navController.navigate(Route.Arena)
                    }

                    RoundedButton(
                        text = "VR ZONE",
                        color = Theme.colors.pinkAction,
                    ) {
                        navController.navigate(Route.Zone)
                    }

                    RoundedButton(
                        text = "ПРАЗДНИКИ И МЕРОПРИЯТИЯ",
                        color = Theme.colors.blueAction,
                    ) {
                        navController.navigate(Route.Holidays)
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
                    startDestination = Route.Arena,
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
}
