package dev.vr.com.presentation.screen.zone

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.layout.GamesGrid
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.GamePopUp
import dev.vr.com.core.components.overlay.InfoVideoPopUp
import dev.vr.com.core.components.text.CustomBackgroundText
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun ZoneScreen(
    viewModel: ZoneViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    var selectedGame by remember { mutableStateOf<GameModel?>(null) }

    var showVideoPopup by remember { mutableStateOf(false) }

    if (showVideoPopup) {
        InfoVideoPopUp(
            image = Res.drawable.zone_popup,
            movieUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
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
                CustomBackgroundText(
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


    selectedGame?.let { game ->
        GamePopUp(
            gameModel = game,
        ) {
            selectedGame = null
        }
    }

    Column {
        Banner(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            background = painterResource(Res.drawable.banner_zone),
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
                    fontFamily = FontFamily(Font(Res.font.Light)),
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
                    },
                    innerPadding = PaddingValues(
                        horizontal = 40.dp,
                        vertical = 8.dp
                    )
                ) {
                    showVideoPopup = true
                }
            }
        )

        RoundedButton(
            modifier = Modifier
                .padding(top = 4.dp),
            color = Theme.colors.pinkAction,
            content = {
                Icon(
                    painter = painterResource(Res.drawable.game_topic),
                    contentDescription = "games",
                    tint = Theme.colors.textInverse,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            innerPadding = PaddingValues(
                vertical = 4.dp
            )
        ) { }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.games.isEmpty()) {
            Text(
                text = "Игр нет",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            GamesGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                games = state.games,
                onClick = { game ->
                    selectedGame = game
                }
            )
        }
    }
}


//private fun getGames() : List<GameModel> {
//    return listOf(
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//        GameModel(
//            image = Res.drawable.zone1,
//            movie = "1.mp4",
//            text = "ALYX",
//            description = "ALYX описание"
//        ),
//    )
//}