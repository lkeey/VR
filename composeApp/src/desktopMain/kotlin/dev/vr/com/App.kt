package dev.vr.com

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vr.com.core.components.layout.AnimatedGradientCirclesBackground
import dev.vr.com.core.theme.Theme
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.InfoPopup
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.layout.TopBar
import dev.vr.com.data.PopupItem
import dev.vr.com.navigation.Route
import dev.vr.com.presentation.ArenaScreen
import dev.vr.com.presentation.HolidaysScreen
import dev.vr.com.presentation.ZoneScreen
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun App() {

    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AnimatedGradientCirclesBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.primaryBackground)
                .padding(horizontal = 20.dp)
        ) {

            TopBar(
                navController = navController
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

fun getItems(): List<PopupItem> {
    return listOf(
        PopupItem(Res.drawable.ic_park_1, "VR аттракционы и автостимуляторы"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
    )
}
