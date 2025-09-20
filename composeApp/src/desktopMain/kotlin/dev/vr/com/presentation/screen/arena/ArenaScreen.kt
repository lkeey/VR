package dev.vr.com.presentation.screen.arena

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.layout.Carousel
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.InfoPopup
import dev.vr.com.core.components.overlay.InfoVideoPopUp
import dev.vr.com.core.components.text.CustomBackgroundText
import dev.vr.com.core.theme.Theme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun ArenaScreen(
    viewModel: ArenaViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    var showInfoPopup by remember { mutableStateOf(false) }
    var showVideoPopup by remember { mutableStateOf(false) }

    if (showInfoPopup) {
        InfoPopup(
            items1st = state.items1st,
            items2nd = state.items2nd,
        ) {
            showInfoPopup = false
        }
    }

    if (showVideoPopup) {
        InfoVideoPopUp(
            image = Res.drawable.video_popup_arena,
            movieUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            onDismiss = {
                showVideoPopup = false

            },
            firstColumn = {
                Column {
                    CustomBackgroundText(
                        content = {
                            Text(
                                text = "ВАС ЖДУТ НЕРЕАЛЬНЫЕ ЭМОЦИИ",
                                color = Theme.colors.textInverse,
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(Res.font.Bold)),
                                fontWeight = FontWeight(700),
                            )
                        },
                        color = Theme.colors.blueAction,
                        shape = Res.drawable.grafity_background
                    )
                    Text(
                        text = "ОТ КОМАНДНЫХ СОСТЯЗАНИЙ\nНА ВИРТУАЛЬНОЙ АРЕНЕ",
                        color = Theme.colors.textInverse,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )
                }
            },
            secondColumn = {
                Column {
                    Icon(
                        painter = painterResource(Res.drawable.vr_arena_name),
                        contentDescription = "arena",
                        tint = Theme.colors.textInverse
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Большое игровое пространство, созданное для проведения захватывающих командных сражений \n" +
                                "в виртуальной реальности и организации незабываемых праздничных мероприятий.",
                        color = Theme.colors.secondaryText,
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(Res.font.Light)),
                        fontWeight = FontWeight(600),
                    )
                }
            },
            bottom = {
                Column {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 2.dp,
                        color = Theme.colors.secondaryGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Спортивно-соревновательная составляющая и эффект полного погружения",
                        color = Theme.colors.blueAction,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "в игру гарантировано подарят НЕЗАБЫВАЕМЫЕ ЭМОЦИИ всем участникам!",
                        color = Theme.colors.textInverse,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )
                }
            }
        )
    }

    Column (
        modifier = modifier
    ) {

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
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                        color = Theme.colors.textInverse
                    )
                },
                description = {
                    Text(
                        text = "ЧТО ЭТО?",
                        fontWeight = FontWeight(400),
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
                        fontFamily = FontFamily(Font(Res.font.Light)),
                        color = Theme.colors.textInverse
                    )
                },
                description = {
                    Text(
                        text = "ПРЕИМУЩЕСТВАХ!",
                        fontWeight = FontWeight(700),
                        fontSize = 56.sp,
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
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
                                fontFamily = FontFamily(Font(Res.font.Bold)),
                                fontWeight = FontWeight(700)
                            )
                        },
                        innerPadding = PaddingValues(
                            horizontal = 40.dp,
                            vertical = 8.dp
                        )
                    ) {
                        showInfoPopup = true
                    }
                }
            )
        }

        /* TODO change to this code */
//        RoundedButton(
//            color = Theme.colors.pinkAction,
//            content = {
//                Icon(
//                    painter = painterResource(Res.drawable.game_topic),
//                    contentDescription = "games",
//                    tint = Theme.colors.textInverse,
//                    modifier = Modifier
//                        .width(60.dp)
//                )
//            },
//            innerPadding = PaddingValues(
//                vertical = 12.dp,
//                horizontal = 16.dp
//            )
//        ) { }

        Image(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(32.dp),
            painter = painterResource(Res.drawable.ic_btn_game),
            contentDescription = "banner background"
        )

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
            Carousel(
                modifier = Modifier
                    .fillMaxWidth(),
                objects = state.games
            )
        }
    }
}
