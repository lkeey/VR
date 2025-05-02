import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.vr.com.core.Theme
import dev.vr.com.core.components.RoundedButton
import dev.vr.com.core.components.VideoPopUp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.img1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    imageUrls: List<DrawableResource>,
    scrollIntervalMs: Long = 3000L
) {

    val pages = imageUrls.chunked(3)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    var selectedImage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(scrollIntervalMs)
            val nextPage = (pagerState.currentPage + 1) % pages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    selectedImage?.let { imageUrl ->
        VideoPopUp(
            videoUrl = Res.drawable.img1,
            description = "Вы выбрали $imageUrl"
        ) {
            selectedImage = null
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
            pages[page].forEachIndexed { index, imageUrl ->
                Column (
                    modifier = Modifier
                        .wrapContentSize(),
                ) {
                    Image(
                        modifier = Modifier
                            .size(300.dp),
                        painter = painterResource(imageUrl),
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
                        selectedImage = imageUrl.toString()
                    }
                }
            }
        }
    }
}