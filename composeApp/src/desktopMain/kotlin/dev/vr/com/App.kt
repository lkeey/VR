package dev.vr.com

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.layout.TopBar
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.database.DatabaseFactory
import dev.vr.com.data.repository.GameRepositoryImpl
import dev.vr.com.presentation.navigation.Route
import dev.vr.com.presentation.screen.arena.ArenaScreen
import dev.vr.com.presentation.screen.holidays.HolidaysScreen
import dev.vr.com.presentation.screen.zone.ZoneScreen
import dev.vr.com.presentation.screen.arena.ArenaViewModel
import dev.vr.com.presentation.screen.holidays.HolidaysViewModel
import dev.vr.com.presentation.screen.settings.SettingsGate
import dev.vr.com.presentation.screen.settings.SettingsScreen
import dev.vr.com.presentation.screen.settings.SettingsViewModel
import dev.vr.com.presentation.screen.zone.ZoneViewModel
import java.io.File

@Composable
fun App() {

    /* DI */
    val db = DatabaseFactory.createDatabase(
        /* TEST */
        path = File(
            System.getProperty("user.home"),
            /* mock_vr.db - for tests */
            "vr.db"
        ).absolutePath
    )
    val gameRepository = GameRepositoryImpl(db = db)

    var currentRoute by remember { mutableStateOf<Route>(Route.Arena) }
    var previousRoute by remember { mutableStateOf<Route?>(null) }

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
                    Route.Settings -> previousRoute == Route.Settings
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
                Route.Arena -> ArenaScreen(viewModel = ArenaViewModel(gameRepository))
                Route.Zone -> ZoneScreen(viewModel = ZoneViewModel(gameRepository))
                Route.Holidays -> HolidaysScreen(viewModel = HolidaysViewModel(gameRepository))
                Route.Settings -> SettingsGate(
                    viewModel = SettingsViewModel(gameRepository),
                    onDismiss = {
                        previousRoute = currentRoute
                        currentRoute = Route.Arena
                    }
                )
            }
        }
    }

}


