package dev.vr.com.presentation

import Carousel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.vr.com.core.Theme
import dev.vr.com.core.components.RoundedText
import dev.vr.com.data.GameModel
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun ArenaScreen(
    navController: NavController
) {
    Column (
        modifier = Modifier
            .padding(top = 8.dp)
    ) {

        Image(
            painter = painterResource(Res.drawable.ic_btn_game),
            contentDescription = "banner background"
        )

        Carousel(games = getGames())
    }
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
            text = "Заголовок 4",
            description = "Описание 4"
        ),
        GameModel(
            image = Res.drawable.img2,
            movie = "movie.mp4",
            text = "Заголовок 5",
            description = "Описание 5"
        ),
        GameModel(
            image = Res.drawable.img3,
            movie = "movie.mp4",
            text = "Заголовок 6",
            description = "Описание 6"
        )
    )
}
