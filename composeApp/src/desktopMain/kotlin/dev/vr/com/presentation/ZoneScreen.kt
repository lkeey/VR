package dev.vr.com.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.InfoVideoPopUp
import dev.vr.com.core.components.text.RoundedText
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.GameModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun ZoneScreen(
    navController: NavController
) {

    var showVideoPopup by remember { mutableStateOf(false) }

    if (showVideoPopup) {
        InfoVideoPopUp(
            image = Res.drawable.zone_popup,
            onCLick = {
                /* TODO */
            },
            onDismiss = {
                showVideoPopup = false
            },
            firstColumn = {
                Column (
                    modifier = Modifier
                        .padding(start = 8.dp)
                ){
                    Text(
                        text = "VR ZONE",
                        color = Theme.colors.textInverse,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                        fontWeight = FontWeight(800),
                    )

                    Text(
                        text = "В VR зоне гости смогут сыграть в лучшие\nиндивидуальные VR игры всех жанров\n" +
                                "и для всех возрастов!",
                        color = Theme.colors.secondaryText,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(Res.font.Light)),
                        fontWeight = FontWeight(600),
                    )
                }
            },
            secondColumn = {
                RoundedText(
                    content = {
                        Text(
                            text = "ШУТЕРЫ, КВЕСТЫ, СПОРТ,  \n" +
                                    "              ФЭНТЕЗИ, ХОРРОР И ДР",
                            color = Theme.colors.textInverse,
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(Res.font.Bold)),
                            fontWeight = FontWeight(700),
                        )
                    },
                    color = Theme.colors.blueAction,
                    shape = Res.drawable.grafity_2x
                )
            },
            bottom = { }
        )
    }

    Column {
        Banner(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            background = painterResource(Res.drawable.zone_background),
            title = {
                Text(
                    text = "VR ZONE",
                    fontWeight = FontWeight(800),
                    fontSize = 56.sp,
                    fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                    color = Theme.colors.textInverse
                )
            },
            description = {
                Text(
                    text = "ЧТО ЭТО?",
                    fontWeight = FontWeight(700),
                    fontSize = 56.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
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
                            fontFamily = FontFamily(Font(Res.font.Bold)),
                            fontWeight = FontWeight(700)
                        )
                    }
                ) {
                    showVideoPopup = true
                }
            }
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            painter = painterResource(Res.drawable.ic_btn_game_big),
            contentDescription = "banner background"
        )

        LazyVerticalGrid(
            columns = GridCells
                .Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(getGames()) { item ->
                Box (
                    modifier = Modifier
                        .clickable (
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            /* TODO */
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(item.image),
                        contentDescription = item.text,
                        contentScale = ContentScale.Crop
                    )

                    Icon(
                        painter = painterResource(Res.drawable.ic_open),
                        contentDescription = "open",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(4.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }

}


private fun getGames() : List<GameModel> {
    return listOf(
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
        GameModel(
            image = Res.drawable.zone1,
            movie = "1.mp4",
            text = "ALYX",
            description = "ALYX описание"
        ),
    )
}