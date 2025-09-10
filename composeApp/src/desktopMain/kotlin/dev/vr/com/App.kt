package dev.vr.com

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.vr.com.core.components.layout.TopBar
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.model.PopupItem
import dev.vr.com.data.repository.GameRepositoryImpl
import dev.vr.com.db.VRDatabase
import dev.vr.com.presentation.navigation.Route
import dev.vr.com.presentation.screen.ArenaScreen
import dev.vr.com.presentation.screen.HolidaysScreen
import dev.vr.com.presentation.screen.ZoneScreen
import dev.vr.com.presentation.screen.settings.SettingsViewModel
import dev.vr.com.presentation.screen.settings.SettingsScreen
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_park_1
import java.io.File

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {

    // DI
    val db = createDatabase()
    val repository = GameRepositoryImpl(db = db)
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
                Route.Arena -> ArenaScreen()
                Route.Zone -> ZoneScreen()
                Route.Holidays -> HolidaysScreen()
                Route.Settings -> SettingsScreen(SettingsViewModel(repository))
            }
        }
    }

}

fun createDatabase(): VRDatabase {
    val dbFile = File(System.getProperty("user.home"), "vr.db")
    val driver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")

    if (!dbFile.exists()) {
        VRDatabase.Schema.create(driver) // создаём таблицы только один раз
    }

    return VRDatabase(driver)
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
