import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.Theme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.core.components.VideoPopUp
import dev.vr.com.data.GameModel
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.ExtraBold
import vr.composeapp.generated.resources.Res

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
            .fillMaxWidth()
            .padding(top = 12.dp),
        pageSpacing = 16.dp
    ) { page ->
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            pages[page].forEachIndexed { index, game ->
                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .clickable { selectedGame = game },
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(game.image),
                        contentDescription = "game image",
                    )

                    Column (
                        modifier = Modifier
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = game.text,
                            fontWeight = FontWeight(700),
                            fontSize = 36.sp,
                            fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                            color = Theme.colors.textInverse
                        )

                        Spacer(
                            Modifier.height(16.dp)
                        )

                        RoundedButton(
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
}
