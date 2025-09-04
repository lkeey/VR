package dev.vr.com.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.vr.com.core.Theme
import dev.vr.com.data.PopupItem
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_close
import vr.composeapp.generated.resources.top_background

@Composable
fun InfoPopup(
    title: String,
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
                                Text(
                                    text = "У НАС В ПАРКЕ:",
                                    color = Theme.colors.textInverse,
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                                    fontWeight = FontWeight(700),
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
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
                                androidx.compose.foundation.Image(
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

            Spacer(modifier = Modifier.width(4.dp))

            androidx.compose.material.Icon(
                painter = painterResource(Res.drawable.ic_close),
                contentDescription = "close",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onDismiss() }
            )
        }
    }
}
