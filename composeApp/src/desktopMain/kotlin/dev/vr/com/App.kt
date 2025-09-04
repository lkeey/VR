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
import dev.vr.com.core.AnimatedGradientCirclesBackground
import dev.vr.com.core.Theme
import dev.vr.com.core.components.Banner
import dev.vr.com.core.components.InfoPopup
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.core.components.TopBar
import dev.vr.com.data.PopupItem
import dev.vr.com.navigation.Route
import dev.vr.com.presentation.ArenaScreen
import dev.vr.com.presentation.HolidaysScreen
import dev.vr.com.presentation.ZoneScreen
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun App() {

    val navController = rememberNavController()

    var showPopup by remember { mutableStateOf(false) }

    if (showPopup) {
        InfoPopup(
            title = "У НАС В ПАРКЕ:",
            items = listOf(
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
        ) {
            showPopup = false
        }
    }

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

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .height(280.dp),
            ) {
                Banner(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    background = painterResource(Res.drawable.back_arena_1),
                    title = {
                        Text(
                            text = "VR АРЕНА",
                            fontWeight = FontWeight(800),
                            fontSize = 56.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.ExtraBold)),
                            color = Theme.colors.textInverse
                        )
                    },
                    description = {
                        Text(
                            text = "ЧТО ЭТО?",
                            fontWeight = FontWeight(400),
                            fontSize = 56.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Light)),
                            color = Theme.colors.textInverse
                        )
                    },
                    button = {
                        RoundedButton(
                            color = Theme.colors.pinkAction,
                            content = {
                                Text(
                                    text = "СМОТРЕТЬ",
                                    color = Theme.colors.textInverse,
                                    fontSize = 32.sp,
                                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                                    fontWeight = FontWeight(700)
                                )
                            }
                        ) {
                            /* TODO */
                        }
                    }
                )

                Spacer(Modifier.width(32.dp))

                Banner(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    background = painterResource(Res.drawable.back_arena_2),
                    title = {
                        Text(
                            text = "УЗНАЙ О НАШИХ",
                            fontWeight = FontWeight(400),
                            fontSize = 56.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Light)),
                            color = Theme.colors.textInverse
                        )
                    },
                    description = {
                        Text(
                            text = "ПРЕИМУЩЕСТВАХ!",
                            fontWeight = FontWeight(700),
                            fontSize = 56.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.ExtraBold)),
                            color = Theme.colors.textInverse
                        )
                    },
                    button = {
                        RoundedButton(
                            color = Theme.colors.blueAction,
                            content = {
                                Text(
                                    text = "СМОТРЕТЬ",
                                    color = Theme.colors.textInverse,
                                    fontSize = 32.sp,
                                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                                    fontWeight = FontWeight(700)
                                )
                            }
                        ) {
                            showPopup = true
                        }
                    }
                )
            }


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
