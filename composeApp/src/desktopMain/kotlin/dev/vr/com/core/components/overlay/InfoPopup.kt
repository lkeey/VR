package dev.vr.com.core.components.overlay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.vr.com.core.theme.Theme
import dev.vr.com.core.components.button.CloseButton
import dev.vr.com.core.components.text.RoundedText
import dev.vr.com.data.PopupItem
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun InfoPopup(
    items: List<PopupItem>,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.7f)
                    .clip(
                        GenericShape { size, _ ->
                            // Размер среза угла (можешь регулировать)
                            val cutX = size.width * 0.05f
                            val cutY = size.height * 0.1f

                            moveTo(0f, 0f)                 // левый верх
                            lineTo(size.width, 0f)         // правый верх
                            lineTo(size.width, size.height - cutY) // правая сторона вниз
                            lineTo(size.width - cutX, size.height) // срезанный угол
                            lineTo(0f, size.height)        // левый низ
                            close()
                        }
                    )
                    .background(Theme.colors.primaryBackground)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        RoundedText(
                            content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
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
                            },
                            color = Theme.colors.grayBackground,
                            shape = Res.drawable.top_background
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells
                            .Fixed(4),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        items(items) { item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(item.image),
                                    contentDescription = item.content,
                                )

                                Spacer(Modifier.height(8.dp))

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = item.content,
                                    color = Theme.colors.textInverse,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                }
            }

            CloseButton(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                onDismiss()
            }
        }
    }
}
