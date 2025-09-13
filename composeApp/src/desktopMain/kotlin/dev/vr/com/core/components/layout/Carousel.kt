package dev.vr.com.core.components.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.vr.com.core.components.item.GameItem
import dev.vr.com.core.components.overlay.GamePopUp
import dev.vr.com.presentation.model.GameModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_btn_left
import vr.composeapp.generated.resources.ic_btn_right

@Composable
fun Carousel(
    modifier: Modifier,
    objects: List<GameModel>,
) {

    val pages = objects.chunked(3)
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
                .fillMaxWidth(),
            pageSpacing = 16.dp
        ) { page ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                pages[page].forEachIndexed { index, game ->

                    GameItem(
                        modifier = Modifier
                            .weight(1f),
                        game = game,
                        index = index,
                        onClick = {
                            selectedGame = game
                        }
                    )

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
