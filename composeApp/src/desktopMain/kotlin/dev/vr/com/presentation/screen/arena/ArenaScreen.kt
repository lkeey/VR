package dev.vr.com.presentation.screen.arena

import dev.vr.com.core.components.layout.Carousel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import dev.vr.com.core.components.overlay.Banner
import dev.vr.com.core.components.overlay.InfoPopup
import dev.vr.com.core.components.overlay.InfoVideoPopUp
import dev.vr.com.core.components.text.CustomBackgroundText
import dev.vr.com.core.theme.Theme
import dev.vr.com.data.model.PopupItem
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
                    Text(
                        text = "VR ARENA",
                        color = Theme.colors.textInverse,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                        fontWeight = FontWeight(800),
                    )

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

//fun getItems(): List<PopupItem> {
//    return listOf(
//        PopupItem(Res.drawable.ic_park_1, "Больше нет головокружений и тошноты"),
//        PopupItem(Res.drawable.ic_park_1, "Безопасно!\n" +
//                "Стены и столбы игровых зон парка оборудованы мягкими протекторами"),
//        PopupItem(Res.drawable.ic_park_1, "Организация и проведение праздничных мероприятий\n" +
//                "и турниров"),
//        PopupItem(Res.drawable.ic_park_1, "Две игровые арены\n" +
//                "Общей площадью 200м2"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//        PopupItem(Res.drawable.ic_park_1, "Командные сражения до 16 игроков"),
//    )
//}

//private fun getGames() : List<GameModel> {
//    return listOf(
//        GameModel(
//            image = Res.drawable.img1,
//            movie = "movie.mp4",
//            text = "SHMOOTER",
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
//            image = Res.drawable.img2,
//            movie = "movie.mp4",
//            text = "KERNEL",
//            description = "Жанр: (PvP) Команда на команду Игроки: 2-10 человека  7+ Площадь: 50м2\u2028\u2028\n" +
//                    "Долгожданная Новинка!\n" +
//                    "Наша Самая продвинутая многоуровневая игра со свободным передвижением!\n" +
//                    "Теперь вам доступна не только реалистичная графика, но и новая механика игры!\n" +
//                    "Команды начинают игру без оружия. Его необходимо найти или постараться отобрать у соперника)). Обойма для оружия находится в подсумке на поясе. Вставь ее в автомат, не забудь взвести затвор и приложить вторую руку к оружию для прицельной стрельбы.\n" +
//                    "Теперь ты готов! отправляйся со своей командой на штурм противника!"
//        ),
//        GameModel(
//            image = Res.drawable.img3,
//            movie = "movie.mp4",
//            text = "SAFE NIGHT",
//            description = "Жанр: (PvE) Зомби Игроки: 2-10 человек 10+ Площадь: 100 м2\u2028\u2028Действие происходит в руинах городов, после ядерной войны в далеком 2041 году, которая закончилась буквально несколько лет назад. Радиация сделала свое дело не оставив шансов человечеству, превратив оставшихся выживших людей в зомби-мутантов. Лишь малая часть людей, в том числе ваша команда, выжила и отправилась на базу военных, сумевших справиться с радиацией и защититься от мутантов и монстров."
//        ),
//        GameModel(
//            image = Res.drawable.img4,
//            movie = "movie.mp4",
//            text = "STARBASE",
//            description = "Жанр: (PvE) Приключенческий шутер Игроки: 2-10 человек 7+ Площадь: 35м2\n" +
//                    "Ваша команда входит в состав экипажа звездного корабля «ВЕГА», основной задачей которого является преодоление огромного расстояния, что бы сменить предыдущих колонистов, которые долгое время находились на аванпосте.\u2028\u2028Обычная рядовая миссия, ну что может пойти не так? )\u2028\u2028"
//        ),
//        GameModel(
//            image = Res.drawable.img5,
//            movie = "movie.mp4",
//            text = "KERNEL Выживание",
//            description = "Жанр: (PvP) Команда на команду Игроки: 2-10 человека  11+ Площадь: 50м2\u2028\u2028\n" +
//                    "Долгожданная Новинка!\n" +
//                    "Самый страшный и реалистичный Квест Зомби Хорор на сегодняшний день!\n" +
//                    "\n" +
//                    "Что бы найти выход, игрокам предстоит решить ряд сложных задач и головоломок.\n" +
//                    "Играйте в команде, взаимодействуйте друг с другом, сражайтесь с зомби и ищите способ выбраться из этого ада!\n" +
//                    "\n" +
//                    "В игре реализована уникальная и реалистичная механика самостоятельной перезарядки оружия.\n" +
//                    "\n" +
//                    "Собери команду и пройди захватывающий Зомби Квест!"
//        ),
//        GameModel(
//            image = Res.drawable.img6,
//            movie = "movie.mp4",
//            text = "STARBASE Выживание",
//            description = "Жанр: (PvE) Приключенческий шутер Игроки: 2-10 человек 9+ Площадь: 35м2\n" +
//                    "Вы и ваша команда оказываются на платформе, окруженной неизвестными формами жизни, эволюционировавшими в нечто опасное. Огромные космические монстры пробудились, реагируя на чужаков. Без возможности эвакуации, ваша главная цель — выжить. Вы вместе со своей командой должны отбиваться от волн все более мощных существ, используя все доступные ресурсы. Лишь слаженная командная работа и эффективное использование оружия помогут вам выжить в этой битве за выживание.\u2028\u2028Обычная рядовая миссия, ну что может пойти не так? )"
//        ),
//        GameModel(
//            image = Res.drawable.img7,
//            movie = "movie.mp4",
//            text = "ДЕТСКИЕ МИНИ ИГРЫ",
//            description = "Жанр: Приключения Игроки: 2-10 человек, 6+ Площадь: 100 м2\u2028\n" +
//                    "Команда юных искателей прибыла в заброшенный замок, в котором спрятано много тайн и загадок. Но, в этом особняке живут не только мыши и тараканы…) Ваша задача пройти все испытания магического замка и выйти из них победителями!"
//        ),
//        GameModel(
//            image = Res.drawable.img8,
//            movie = "movie.mp4",
//            text = "CYBERACTION",
//            description = "Жанр: (PvP) Команда на команду Игроки: 2-8 человек 7+ Площадь: 220м2\n" +
//                    "\n" +
//                    "CYBERACTION – продвинутый аналог культовой игры COUNTER STRIKE в формате VR.\n" +
//                    "\n" +
//                    "Но, это не компьютерная игра, где вы играете за персонажа. Здесь вы становитесь героем игры и попадаете в самую гущу командных сражений на нашей арене!\n" +
//                    "\n" +
//                    "Действие происходит более чем на 14 локациях (стройка, форт, бункер, лаборатория, офис…), где террористам противостоит элитный спецназ, задача которого нейтрализовать противника.\n" +
//                    "В распоряжении каждой команды имеется разнообразный арсенал оружия, которым можно пользоваться на протяжении всего сражения. Игроки делятся на две команды (синяя и красная) и начинают штурм противника с противоположных сторон карты. За каждого уничтоженного игрока противника вы зарабатываете очки и улучшаете собственный рекорд по итогам игры.\u2028Эффективность уничтожения противника и последующая победа, будут зависеть от того, на сколько слаженно и продуманно действуют игроки обеих команд. Разрабатывайте стратегию, продумывайте тактику игры и прикрывайте друг друга во время атак."
//        )
//    )
//}
