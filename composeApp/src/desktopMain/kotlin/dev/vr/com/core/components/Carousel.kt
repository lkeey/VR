import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.Theme
import dev.vr.com.core.components.RoundedButton
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    imageUrls: List<DrawableResource>,
    scrollIntervalMs: Long = 3000L
) {

    val pages = imageUrls.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(scrollIntervalMs)
            val nextPage = (pagerState.currentPage + 1) % pages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        pageSpacing = 16.dp
    ) { page ->
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            pages[page].forEach { imageUrl ->
                Image(
                    painter = painterResource(imageUrl),
                    contentDescription = "img",
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                )
                RoundedButton(
                    text = "VR ARENA",
                    color = Theme.colors.blueAction,
                ) {

                }
            }
        }
    }
}