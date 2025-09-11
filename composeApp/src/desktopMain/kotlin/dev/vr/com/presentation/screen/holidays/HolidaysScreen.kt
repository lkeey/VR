package dev.vr.com.presentation.screen.holidays

import Carousel
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
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
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.InfoVideoPopUp
import dev.vr.com.core.components.text.RoundedText
import dev.vr.com.core.theme.Theme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun HolidaysScreen(
    viewModel: HolidaysViewModel,
    modifier: Modifier = Modifier
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

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
                        text = "ПРАЗДНИКИ И МЕРОПРИЯТИЯ",
                        color = Theme.colors.textInverse,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                        fontWeight = FontWeight(800),
                    )

                    Text(
                        text = "Ламповая атмосфера парка и комфортная" +
                                "комната отдыха позволят провести" +
                                "незабываемое праздничное событие!",
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
                            text = "ОРГАНИЗУЕМ НЕОБЫКНОВЕННОЕ И\n" +
                                    "ЗАПОМИНАЮЩЕЕСЯ МЕРОПРИЯТИЕ!",
                            color = Theme.colors.textInverse,
                            fontSize = 36.sp,
                            fontFamily = FontFamily(Font(Res.font.Bold)),
                            fontWeight = FontWeight(600),
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
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            background = painterResource(Res.drawable.holidays_background),
            title = {
                Text(
                    text = "ПРАЗДНИКИ И",
                    fontWeight = FontWeight(800),
                    fontSize = 56.sp,
                    fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                    color = Theme.colors.textInverse
                )
            },
            description = {
                Text(
                    text = "МЕРОПРИЯТИЯ",
                    fontWeight = FontWeight(800),
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

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.holidays.isEmpty()) {
            Text(
                text = "Праздников нет",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Carousel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                objects = state.holidays
            )
        }
    }
}

//private fun getGames() : List<GameModel> {
//    return listOf(
//        GameModel(
//            image = Res.drawable.holidays_1,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЙ ДЕНЬ РОЖДЕНИЯ",
//            description = "\n" +
//                    "Жанр: (PvP) Команда на команду Игроки: 2-16 человек 7+ Площадь: От 140м2 до 280м2\n" +
//                    "\n" +
//                    "Добро пожаловать на арену киберсборта – SHMOOTER!\n" +
//                    "Уникальная командная игра, созданная специально для игровых арен со свободным перемещением, и не имеющая аналогов в мире!\n" +
//                    "\n" +
//                    "Только в SHMOOTER игроки могут перемещаться по этажам на лифтах, телепортироваться в другие здания через порталы и даже ходить над головой соперников!\n" +
//                    "Возможности игры позволяют делиться на четыре команды и одновременно участвовать в мультяшном сражении до 16 человек!\n" +
//                    "\n" +
//                    "Защищай базу, захватывай точки, ищи бонусное оружие и прикрывай свою команду! Еще остались силы?! Тогда устрой мультяшный беспредел в режиме «Каждый сам за себя!»"
//        ),
//        GameModel(
//            image = Res.drawable.holidays_1,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЙ ДЕНЬ РОЖДЕНИЯ",
//            description = "\n" +
//                    "Жанр: (PvP) Команда на команду Игроки: 2-16 человек 7+ Площадь: От 140м2 до 280м2\n" +
//                    "\n" +
//                    "Добро пожаловать на арену киберсборта – SHMOOTER!\n" +
//                    "Уникальная командная игра, созданная специально для игровых арен со свободным перемещением, и не имеющая аналогов в мире!\n" +
//                    "\n" +
//                    "Только в SHMOOTER игроки могут перемещаться по этажам на лифтах, телепортироваться в другие здания через порталы и даже ходить над головой соперников!\n" +
//                    "Возможности игры позволяют делиться на четыре команды и одновременно участвовать в мультяшном сражении до 16 человек!\n" +
//                    "\n" +
//                    "Защищай базу, захватывай точки, ищи бонусное оружие и прикрывай свою команду! Еще остались силы?! Тогда устрой мультяшный беспредел в режиме «Каждый сам за себя!»"
//        ),
//        GameModel(
//            image = Res.drawable.holidays_1,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЙ ДЕНЬ РОЖДЕНИЯ",
//            description = "\n" +
//                    "Жанр: (PvP) Команда на команду Игроки: 2-16 человек 7+ Площадь: От 140м2 до 280м2\n" +
//                    "\n" +
//                    "Добро пожаловать на арену киберсборта – SHMOOTER!\n" +
//                    "Уникальная командная игра, созданная специально для игровых арен со свободным перемещением, и не имеющая аналогов в мире!\n" +
//                    "\n" +
//                    "Только в SHMOOTER игроки могут перемещаться по этажам на лифтах, телепортироваться в другие здания через порталы и даже ходить над головой соперников!\n" +
//                    "Возможности игры позволяют делиться на четыре команды и одновременно участвовать в мультяшном сражении до 16 человек!\n" +
//                    "\n" +
//                    "Защищай базу, захватывай точки, ищи бонусное оружие и прикрывай свою команду! Еще остались силы?! Тогда устрой мультяшный беспредел в режиме «Каждый сам за себя!»"
//        ),
//        GameModel(
//            image = Res.drawable.holidays_1,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЙ ДЕНЬ РОЖДЕНИЯ",
//            description = "\n" +
//                    "Жанр: (PvP) Команда на команду Игроки: 2-16 человек 7+ Площадь: От 140м2 до 280м2\n" +
//                    "\n" +
//                    "Добро пожаловать на арену киберсборта – SHMOOTER!\n" +
//                    "Уникальная командная игра, созданная специально для игровых арен со свободным перемещением, и не имеющая аналогов в мире!\n" +
//                    "\n" +
//                    "Только в SHMOOTER игроки могут перемещаться по этажам на лифтах, телепортироваться в другие здания через порталы и даже ходить над головой соперников!\n" +
//                    "Возможности игры позволяют делиться на четыре команды и одновременно участвовать в мультяшном сражении до 16 человек!\n" +
//                    "\n" +
//                    "Защищай базу, захватывай точки, ищи бонусное оружие и прикрывай свою команду! Еще остались силы?! Тогда устрой мультяшный беспредел в режиме «Каждый сам за себя!»"
//        ),
//        GameModel(
//            image = Res.drawable.holidays_1,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЙ ДЕНЬ РОЖДЕНИЯ",
//            description = "\n" +
//                    "Жанр: (PvP) Команда на команду Игроки: 2-16 человек 7+ Площадь: От 140м2 до 280м2\n" +
//                    "\n" +
//                    "Добро пожаловать на арену киберсборта – SHMOOTER!\n" +
//                    "Уникальная командная игра, созданная специально для игровых арен со свободным перемещением, и не имеющая аналогов в мире!\n" +
//                    "\n" +
//                    "Только в SHMOOTER игроки могут перемещаться по этажам на лифтах, телепортироваться в другие здания через порталы и даже ходить над головой соперников!\n" +
//                    "Возможности игры позволяют делиться на четыре команды и одновременно участвовать в мультяшном сражении до 16 человек!\n" +
//                    "\n" +
//                    "Защищай базу, захватывай точки, ищи бонусное оружие и прикрывай свою команду! Еще остались силы?! Тогда устрой мультяшный беспредел в режиме «Каждый сам за себя!»"
//        ),
//    )
//}