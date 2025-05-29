import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.Theme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.core.components.VideoPopUp
import dev.vr.com.data.GameModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    games: List<GameModel>,
    scrollIntervalMs: Long = 3000L
) {

    val pages = games.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    var selectedGame by remember { mutableStateOf<GameModel?>(null) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(scrollIntervalMs)
            val nextPage = (pagerState.currentPage + 1) % pages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    selectedGame?.let { game ->
        VideoPopUp(
            text = game.text,
            videoUrl = game.movie,
            description = "Вы выбрали ${game.description}"
        ) {
            selectedGame = null
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth(),
        pageSpacing = 16.dp
    ) { page ->
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            pages[page].forEachIndexed { index, game ->
                Column (
                    modifier = Modifier
                        .wrapContentSize(),
                ) {
                    Image(
                        modifier = Modifier
                            .size(300.dp)
                            .clickable {
                                selectedGame = game
                            },
                        painter = painterResource(game.image),
                        contentDescription = "img",
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    RoundedButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = "СМОТРЕТЬ",
                        color = when (index % 2) {
                            0 -> Theme.colors.blueAction
                            else -> Theme.colors.pinkAction
                        }
                    ) {
                        selectedGame = game
                    }
                }
            }
        }
    }
}