import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.theme.Theme
import dev.vr.com.core.components.button.RoundedButton
import dev.vr.com.core.components.overlay.GamePopUp
import dev.vr.com.data.GameModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_btn_left
import vr.composeapp.generated.resources.ic_btn_right

@Composable
fun Carousel(
    modifier: Modifier,
    games: List<GameModel>,
) {

    val pages = games.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    var selectedGame by remember { mutableStateOf<GameModel?>(null) }

    /* auto-scrolling */
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(scrollIntervalMs)
//            val nextPage = (pagerState.currentPage + 1) % pages.size
//            pagerState.animateScrollToPage(nextPage)
//        }
//    }

    selectedGame?.let { game ->
        GamePopUp(
            gameModel = game,
        ) {
            selectedGame = null
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

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
                            .weight(1f)
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

                        Column(
                            modifier = Modifier
                                .padding(20.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = game.text,
                                fontWeight = FontWeight(700),
                                fontSize = 36.sp,
                                fontFamily = FontFamily(Font(Res.font.Bold)),
                                color = Theme.colors.textInverse,
                                textAlign = TextAlign.Center
                            )

                            Spacer(
                                Modifier.height(16.dp)
                            )

                            RoundedButton(
                                color = when (index % 2) {
                                    0 -> Theme.colors.blueAction
                                    else -> Theme.colors.pinkAction
                                },
                                content = {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        text = "СМОТРЕТЬ",
                                        color = Theme.colors.textInverse,
                                        fontSize = 26.sp,
                                        fontFamily = FontFamily(Font(Res.font.Bold)),
                                        fontWeight = FontWeight(700),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            ) {
                                selectedGame = game
                            }
                        }
                    }

                    if (index != pages[page].lastIndex) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
        }

        /* Buttons for next/previous */
        if (pagerState.currentPage > 0) {
            Icon(
                painter = painterResource(Res.drawable.ic_btn_left),
                contentDescription = "left",
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
            )
        }

        if (pagerState.currentPage < pages.size - 1) {
            Icon(
                painter = painterResource(Res.drawable.ic_btn_right),
                contentDescription = "left",
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(start = 16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
            )
        }
    }
}
