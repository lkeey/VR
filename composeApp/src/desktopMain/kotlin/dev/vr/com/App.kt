package dev.vr.com

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.layout.TopBar
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.database.DatabaseFactory
import dev.vr.com.data.model.PopupItem
import dev.vr.com.data.repository.GameRepositoryImpl
import dev.vr.com.domain.model.GameModel
import dev.vr.com.domain.usecase.AddGameUseCase
import dev.vr.com.presentation.navigation.Route
import dev.vr.com.presentation.screen.ArenaScreen
import dev.vr.com.presentation.screen.HolidaysScreen
import dev.vr.com.presentation.screen.ZoneScreen
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_park_1

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {

    // DI
    val db = DatabaseFactory.createDatabase()
//    val addGameUseCase = AddGameUseCase(GameRepositoryImpl(db))

    var currentRoute by remember { mutableStateOf<Route>(Route.Arena) }
    var previousRoute by remember { mutableStateOf<Route?>(null) }

//    val navController = rememberNavController()

//        AnimatedGradientCirclesBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.primaryBackground)
            .padding(horizontal = 20.dp)
    ) {

        TopBar(
            currentRoute = currentRoute,
            onNavigate = { newRoute ->
                previousRoute = currentRoute
                currentRoute = newRoute
            },
        )

        AnimatedContent(
            targetState = currentRoute,
            modifier = Modifier.fillMaxSize(),
            transitionSpec = {
                val forward = when (targetState) {
                    Route.Arena -> previousRoute != Route.Arena
                    Route.Zone -> previousRoute == Route.Arena
                    Route.Holidays -> previousRoute == Route.Zone
                }

                slideInHorizontally(
                    initialOffsetX = { fullWidth -> if (forward) fullWidth else -fullWidth },
                    animationSpec = tween(300)
                ).togetherWith(
        slideOutHorizontally(
                    targetOffsetX = { fullWidth -> if (forward) -fullWidth else fullWidth },
                    animationSpec = tween(300)
                ))
            }
        ) { target ->
            when (target) {
                Route.Arena -> ArenaScreen()
                Route.Zone -> ZoneScreen()
                Route.Holidays -> HolidaysScreen()
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
