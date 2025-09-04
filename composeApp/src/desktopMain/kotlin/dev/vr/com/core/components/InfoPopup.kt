package dev.vr.com.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindow
import dev.vr.com.core.Theme
import dev.vr.com.data.PopupItem
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.Bold
import vr.composeapp.generated.resources.Res
import vr.composeapp.generated.resources.ic_close

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
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.8f)
                .background(Theme.colors.grayBackground)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "У НАС В ПАРКЕ:",
                        fontWeight = FontWeight(800),
                        fontSize = 56.sp,
                        fontFamily = FontFamily(org.jetbrains.compose.resources.Font(Res.font.Bold)),
                        color = Theme.colors.textInverse
                    )

                    RoundedText (
                        text = "У НАС В ПАРКЕ:",
                        color = Theme.colors.blueAction
                    )

//                    androidx.compose.foundation.Image(
//                        painter = painterResource(Res.drawable.ic_title),
//                        contentDescription = "title"
//                    )

                    androidx.compose.material.Icon(
                        painter = painterResource(Res.drawable.ic_close),
                        contentDescription = "Закрыть",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { onDismiss() }
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
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(item.image),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = item.content,
                                color = Theme.colors.textInverse
                            )
                        }
                    }
                }

            }
        }
    }

}
