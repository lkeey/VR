package dev.vr.com.core.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.theme.Theme
import dev.vr.com.navigation.Route
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun TopBar(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(100.dp)
            .clip(
                GenericShape { size, _ ->
                    // Размер среза угла
                    val cutX = size.width * 0.02f
                    val cutY = size.height * 0.3f

                    moveTo(0f, 0f)                 // левый верх
                    lineTo(size.width, 0f)         // правый верх
                    lineTo(size.width, size.height - cutY) // правая сторона вниз
                    lineTo(size.width - cutX, size.height) // срезанный угол
                    lineTo(0f, size.height)        // левый низ
                    close()
                }
            )
            .background(Theme.colors.grayBackground)
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .weight(0.5f)
                .height(60.dp),
            tint = Color.Unspecified,
            painter = painterResource(Res.drawable.logo_vs_arena),
            contentDescription = "logo"
        )

        Icon(
            modifier = Modifier
                .weight(0.5f)
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ){
                    /* TODO */
                },
            tint = Color.Unspecified,
            painter = painterResource(Res.drawable.name_top),
            contentDescription = "name"
        )

        RoundedButton(
            modifier = Modifier
                .weight(1f),
            color =
                if (currentDestination == Route.Arena::class.qualifiedName) Theme.colors.blueAction
                else Theme.colors.pinkAction,
            content = {
                Icon(
                    painter = painterResource(Res.drawable.vr_arena_name),
                    contentDescription = "arena",
                    tint = Theme.colors.textInverse
                )
            }
        ) {
            navController.navigate(Route.Arena)
        }

        RoundedButton(
            modifier = Modifier
                .weight(1f),
            color =
                if (currentDestination == Route.Zone::class.qualifiedName) Theme.colors.blueAction
                else Theme.colors.pinkAction,
            content = {
                Icon(
                    painter = painterResource(Res.drawable.vr_zone_name),
                    contentDescription = "arena",
                    tint = Theme.colors.textInverse
                )
            }
        ) {
            navController.navigate(Route.Zone)
        }

        RoundedButton(
            modifier = Modifier
                .weight(2f)
                .padding(12.dp),
            color =
                if (currentDestination == Route.Holidays::class.qualifiedName) Theme.colors.blueAction
                else Theme.colors.pinkAction,
            content = {
                Icon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    painter = painterResource(Res.drawable.holidays_name),
                    contentDescription = "arena",
                    tint = Theme.colors.textInverse
                )
            }
        ) {
            navController.navigate(Route.Holidays)
        }

        Icon(
            modifier = Modifier
                .weight(0.5f)
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    /* TODO */
                }
                .height(60.dp),
            tint = Color.Unspecified,
            painter = painterResource(Res.drawable.ic_settings),
            contentDescription = "settings"
        )
    }
}
