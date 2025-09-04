package dev.vr.com.presentation

import Carousel
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.vr.com.data.GameModel
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.img1
import vr.composeapp.generated.resources.img2
import vr.composeapp.generated.resources.img3

@Composable
fun ArenaScreen(
    navController: NavController
) {
    Carousel(games = getGames())
}

private fun getGames() : List<GameModel> {
    return listOf(
        GameModel(
            image = Res.drawable.img1,
            movie = "movie.mp4",
            text = "Заголовок 1",
            description = "Описание 1"
        ),
        GameModel(
            image = Res.drawable.img2,
            movie = "movie.mp4",
            text = "Заголовок 2",
            description = "Описание 2"
        ),
        GameModel(
            image = Res.drawable.img3,
            movie = "movie.mp4",
            text = "Заголовок 3",
            description = "Описание 3"
        ),
        GameModel(
            image = Res.drawable.img1,
            movie = "movie.mp4",
            text = "Заголовок 1",
            description = "Описание 1"
        ),
        GameModel(
            image = Res.drawable.img2,
            movie = "movie.mp4",
            text = "Заголовок 2",
            description = "Описание 2"
        ),
        GameModel(
            image = Res.drawable.img3,
            movie = "movie.mp4",
            text = "Заголовок 3",
            description = "Описание 3"
        )
    )
}
