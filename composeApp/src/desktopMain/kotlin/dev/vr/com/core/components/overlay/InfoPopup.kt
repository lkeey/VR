package dev.vr.com.core.components.overlay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.vr.com.core.components.text.RoundedText
import dev.vr.com.core.theme.Theme
import dev.vr.com.presentation.model.GameModel
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.ExtraBold
import vr.composeapp.generated.resources.Light
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.name_top

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InfoPopup(
    items1st: List<GameModel>,
    items2nd: List<GameModel>,
    onDismiss: () -> Unit
) {
    VRPopUp(
        onDismiss = onDismiss,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.7f)
                .clip(
                    GenericShape { size, _ ->
                        val cutX = size.width * 0.05f
                        val cutY = size.height * 0.1f

                        moveTo(0f, 0f)
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height - cutY)
                        lineTo(size.width - cutX, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                )
                .background(Theme.colors.primaryBackground)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Заголовок 1
                RoundedText(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Box(Modifier.fillMaxWidth()) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterStart),
                                tint = Color.Unspecified,
                                painter = painterResource(Res.drawable.name_top),
                                contentDescription = "name"
                            )
                            Row(
                                modifier = Modifier.align(Alignment.TopCenter)
                            ) {
                                Text(
                                    text = "У НАС В",
                                    color = Theme.colors.textInverse,
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(Font(Res.font.Light)),
                                    fontWeight = FontWeight(600),
                                )
                                Text(
                                    text = " ПАРКЕ:",
                                    color = Theme.colors.textInverse,
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                                    fontWeight = FontWeight(800),
                                )
                            }
                        }
                    },
                    color = Theme.colors.grayBackground,
                )

                Spacer(Modifier.height(16.dp))

                // Первый блок
                FlowRow(
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items1st.forEach { item ->
                        InfoPopUpItem(
                            modifier = Modifier
                                .weight(1f),
                            item = item
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Заголовок 2
                RoundedText(
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Box(Modifier.fillMaxWidth()) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterStart),
                                tint = Color.Unspecified,
                                painter = painterResource(Res.drawable.name_top),
                                contentDescription = "name"
                            )
                            Row(
                                modifier = Modifier.align(Alignment.TopCenter)
                            ) {
                                Text(
                                    text = "О НАШИХ",
                                    color = Theme.colors.textInverse,
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(Font(Res.font.Light)),
                                    fontWeight = FontWeight(600),
                                )
                                Text(
                                    text = " ИГРАХ:",
                                    color = Theme.colors.textInverse,
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                                    fontWeight = FontWeight(800),
                                )
                            }
                        }
                    },
                    color = Theme.colors.grayBackground,
                )

                Spacer(Modifier.height(16.dp))

                // Второй блок
                FlowRow(
                    maxItemsInEachRow = 4,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items2nd.forEach { item ->
                        InfoPopUpItem(
                            modifier = Modifier
                                .weight(1f),
                            item = item
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoPopUpItem(
    item : GameModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .width(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = item.image,
            contentDescription = item.text,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = item.text,
            color = Theme.colors.textInverse,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(Res.font.Light)),
            fontWeight = FontWeight(700),
        )
    }
}
